package ch.fantasticgame28.tc.blockentity.custom;

import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import ch.fantasticgame28.tc.gui.SolarPanelScreenHandler;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.EnergyStorage;
import team.reborn.energy.api.EnergyStorageUtil;
import team.reborn.energy.api.base.SimpleEnergyStorage;

@SuppressWarnings("UnstableApiUsage")
public class SolarPanelBlockEntity extends BlockEntity implements NamedScreenHandlerFactory, IImplementedInventory {

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(
            4*4000, 0, 20) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };

    protected final PropertyDelegate propertyDelegate;


    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.SOLAR_PANEL_BLOCK_ENTITY, pos, state);
        propertyDelegate = new PropertyDelegate() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> (int) SolarPanelBlockEntity.this.energyStorage.amount;
                    case 1 -> (int) SolarPanelBlockEntity.this.energyStorage.capacity;
                    default -> 0;
                };
            }

            @Override
            public void set(int index, int value) {
                if (index == 0) {
                    SolarPanelBlockEntity.this.energyStorage.amount = value;
                }
            }

            @Override
            public int size() {
                return 2;
            }
        };
    }

    public static void tick(World world1, BlockPos pos, BlockState state1, SolarPanelBlockEntity be) {
        if(!world1.isClient) {
            if (be.energyStorage.amount < be.energyStorage.capacity &&
                    world1.isDay() && world1.isSkyVisible(pos)) {
                be.energyStorage.amount += 3;
            }
            for (int x = 0; x < 6; x++) {
                Direction direction = Direction.byId(x);
                EnergyStorage maybeStorage = EnergyStorage.SIDED.find(world1, pos.offset(direction), direction.getOpposite());
                if(maybeStorage != null) {
                    long amountMoved = EnergyStorageUtil.move(
                            be.energyStorage,
                            maybeStorage,
                            20,
                            null
                    );
                    be.energyStorage.amount -= amountMoved;
                }

            }

        }
        be.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        nbt.putLong("energy", energyStorage.amount);

        super.writeNbt(nbt);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);

        energyStorage.amount = nbt.getLong("energy");
    }


    @Override
    public Text getDisplayName() {
        return Text.translatable(getCachedState().getBlock().getTranslationKey());
    }

    @Nullable
    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory inv, PlayerEntity player) {
        return new SolarPanelScreenHandler(syncId, this.propertyDelegate);
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return null;
    }
}
