package ch.fantasticgame28.tc.blockentity.custom;

import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import org.jetbrains.annotations.Nullable;
import team.reborn.energy.api.base.SimpleEnergyStorage;

public class SolarPanelBlockEntity extends BlockEntity {

    public final SimpleEnergyStorage energyStorage = new SimpleEnergyStorage(
            5*4000, 0, 20) {
        @Override
        protected void onFinalCommit() {
            markDirty();
        }
    };


    public SolarPanelBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntity.SOLAR_PANEL_BLOCK_ENTITY, pos, state);
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

    public void tick() {

    }


}
