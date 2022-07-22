package ch.fantasticgame28.tc.blockentity.custom;

import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import ch.fantasticgame28.tc.recipe.HeaterRecipe;
import com.mojang.brigadier.LiteralMessage;
import io.wispforest.owo.blockentity.LinearProcess;
import io.wispforest.owo.util.ImplementedInventory;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.LiteralTextContent;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

import java.util.Optional;

@SuppressWarnings("UnstableApiUsage")
public class HeaterBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, ImplementedInventory {

    private final DefaultedList<ItemStack> inventory =
            DefaultedList.ofSize(2, ItemStack.EMPTY);

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(
            4000, 10, 0) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    protected final PropertyDelegate propertyDelegate;
    private int progress = 0;
    private int maxProgress = 1200;

    public HeaterBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.HEATER_BLOCK_ENTITY, pos, state);

        propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) HeaterBlockEntity.this.energyStorage.amount;
                    case 1 -> (int) HeaterBlockEntity.this.energyStorage.capacity;
                    case 2 -> (int) HeaterBlockEntity.this.progress;
                    case 3 -> (int) HeaterBlockEntity.this.maxProgress;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0: HeaterBlockEntity.this.energyStorage.amount = value; break;
                    case 1: break;
                    case 2: HeaterBlockEntity.this.progress = value; break;
                    case 3: HeaterBlockEntity.this.maxProgress = value;
                }
            }

            @Override
            public int size() {
                return 4;
            }


        };
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        Inventories.writeNbt(nbt, inventory);
        nbt.putLong("energyStorage.amount", energyStorage.amount);
        nbt.putInt("progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        Inventories.readNbt(nbt, inventory);
        super.readNbt(nbt);
        energyStorage.amount = nbt.getLong("energyStorage.amount");
        progress = nbt.getInt("progress");
    }

    public static void tick(World world1, BlockPos pos, BlockState state, HeaterBlockEntity be) {
        if(be.progress > 0) {
            be.energyStorage.amount -= 10;
        }

        if(hasRecipe(be)) {
            if (!(be.energyStorage.amount < 0)) {
                if (be.progress > be.maxProgress) {
                    craftItem(be);
                }
            }
        } else {
            be.resetProgress();
        }
    }

    private static boolean hasRecipe(HeaterBlockEntity be) {
        World world = be.world;
        SimpleInventory inventory = new SimpleInventory(be.inventory.size());
        for (int i = 0; i < be.inventory.size(); i++) {
            inventory.setStack(i, be.getStack(i));
        }

        Optional<HeaterRecipe> match = world.getRecipeManager()
                .getFirstMatch(HeaterRecipe.Type.INSTANCE, inventory, world);

        return match.isPresent() && canInsertAmountIntoOutputSlot(inventory)
                && canInsertItemIntoOutputSlot(inventory, match.get().getOutput());
    }

    private static void craftItem(HeaterBlockEntity be) {
        World world = be.world;
        SimpleInventory inventory = new SimpleInventory(be.inventory.size());
        for (int i = 0; i < be.inventory.size(); i++) {
            inventory.setStack(i, be.getStack(i));
        }

        Optional<HeaterRecipe> match = world.getRecipeManager()
                .getFirstMatch(HeaterRecipe.Type.INSTANCE, inventory, world);

        if(match.isPresent()) {
            be.removeStack(1, 1);

            be.setStack(1, new ItemStack(match.get().getOutput().getItem(),
                    be.getStack(1).getCount() + 1));

            be.resetProgress();
        }
    }

    private void resetProgress() {
        progress = 0;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return Text.literal("Heater");
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return null;
    }

    private static boolean canInsertItemIntoOutputSlot(SimpleInventory inventory, ItemStack output) {
        return inventory.getStack(1).getItem() == output.getItem() || inventory.getStack(1).isEmpty();
    }

    private static boolean canInsertAmountIntoOutputSlot(SimpleInventory inventory) {
        return inventory.getStack(1).getMaxCount() > inventory.getStack(1).getCount();
    }
}
