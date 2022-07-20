package ch.fantasticgame28.tc.item;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.block.ModBlocks;
import io.wispforest.owo.itemgroup.Icon;
import io.wispforest.owo.itemgroup.OwoItemGroup;
import io.wispforest.owo.itemgroup.gui.ItemGroupButton;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.tag.TagKey;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class ModItems {

    /* public static final ItemGroup MOD_ITEM_GROUP =
            FabricItemGroupBuilder.build(new Identifier(TechnicalCreation.MOD_ID, "general"),
                    () -> ModItems.SOLAR_PANEL_ITEM.getDefaultStack()); */
    public static final TagKey<Item> MACHINES = TagKey.of(Registry.ITEM_KEY, new Identifier(TechnicalCreation.MOD_ID, "machines"));

    public static final TagKey<Item> GENERATORS = TagKey.of(Registry.ITEM_KEY, new Identifier(TechnicalCreation.MOD_ID, "generators"));

    public static final OwoItemGroup MOD_ITEM_GROUP = new OwoItemGroup(new Identifier(TechnicalCreation.MOD_ID, "general")) {
        @Override
        protected void setup() {
            keepStaticTitle();

            addTab(Icon.of(ModItems.HEATER), "everything", null);
            addTab(Icon.of(ModItems.HEATER), "machines", MACHINES);
            addTab(Icon.of(ModItems.HEATER), "generators", GENERATORS);

            addButton(ItemGroupButton.github("https://github.com/RoBaertschi/TechnicalCreation"));
        }

        @Override
        public ItemStack createIcon() {
            return ModItems.HEATER.getDefaultStack();
        }
    };

    public static BlockItem SOLAR_PANEL_ITEM =
            Registry.register(Registry.ITEM, new Identifier(TechnicalCreation.MOD_ID, "solar_panel"),
                    new BlockItem(ModBlocks.SOLAR_PANEL_BLOCK, new FabricItemSettings().group(MOD_ITEM_GROUP)));

    public static BlockItem HEATER =
            Registry.register(Registry.ITEM, new Identifier(TechnicalCreation.MOD_ID,"heater"),
                    new BlockItem(ModBlocks.HEATER_BLOCK, new FabricItemSettings().group(MOD_ITEM_GROUP)));

    public static void registerItems() {
        TechnicalCreation.LOGGER.info("Registering Items");
        MOD_ITEM_GROUP.initialize();
    }
}
