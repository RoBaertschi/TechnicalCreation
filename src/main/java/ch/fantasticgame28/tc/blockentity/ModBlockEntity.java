package ch.fantasticgame28.tc.blockentity;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.block.ModBlocks;
import ch.fantasticgame28.tc.blockentity.custom.HeaterBlockEntity;
import ch.fantasticgame28.tc.blockentity.custom.SolarPanelBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import team.reborn.energy.api.EnergyStorage;

public class ModBlockEntity {

    public static final BlockEntityType<SolarPanelBlockEntity> SOLAR_PANEL_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, TechnicalCreation.MOD_ID + ":solar_panel_block_entity",
                    FabricBlockEntityTypeBuilder.create(SolarPanelBlockEntity::new, ModBlocks.SOLAR_PANEL_BLOCK).build(null));

    public static final BlockEntityType<HeaterBlockEntity> HEATER_BLOCK_ENTITY =
            Registry.register(Registry.BLOCK_ENTITY_TYPE, TechnicalCreation.MOD_ID + ":heater_block_entity",
                    FabricBlockEntityTypeBuilder.create(HeaterBlockEntity::new, ModBlocks.HEATER_BLOCK).build(null));

    public static void registerBlockEntities() {
        TechnicalCreation.LOGGER.info("Registering Block Entities");
        EnergyStorage.SIDED.registerForBlockEntity((be, direction) -> be.energyStorage, SOLAR_PANEL_BLOCK_ENTITY);
        EnergyStorage.SIDED.registerForBlockEntity(((blockEntity, direction) -> blockEntity.energyStorage), HEATER_BLOCK_ENTITY);
    }
}
