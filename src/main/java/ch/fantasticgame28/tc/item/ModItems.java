package ch.fantasticgame28.tc.item;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.block.ModBlocks;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    public static BlockItem SOLAR_PANEL_ITEM =
            Registry.register(Registry.ITEM, new Identifier(TechnicalCreation.MOD_ID, "solar_panel"),
                    new BlockItem(ModBlocks.SOLAR_PANEL_BLOCK, new FabricItemSettings().group(ItemGroup.REDSTONE)));


    public static void registerItems() {
        TechnicalCreation.LOGGER.info("Registering Items");
    }
}
