package ch.fantasticgame28.tc.block;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.block.custom.SolarPanelBlock;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModBlocks {

    public static final Block SOLAR_PANEL_BLOCK = Registry.register(
            Registry.BLOCK, new Identifier(TechnicalCreation.MOD_ID, "solar_panel"), new SolarPanelBlock(FabricBlockSettings.of(Material.METAL).strength(4.0f)));

    public static void registerBlocks() {
        TechnicalCreation.LOGGER.info("Registering Blocks");
    }
}
