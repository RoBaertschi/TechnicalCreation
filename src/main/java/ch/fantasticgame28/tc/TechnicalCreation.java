package ch.fantasticgame28.tc;

import ch.fantasticgame28.tc.block.ModBlocks;
import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import ch.fantasticgame28.tc.gui.ModScreenHandler;
import ch.fantasticgame28.tc.gui.SolarPanelScreenHandler;
import ch.fantasticgame28.tc.item.ModItems;
import ch.fantasticgame28.tc.recipe.ModRecipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TechnicalCreation implements ModInitializer {
	public static final String MOD_ID = "tc";
	public static final Logger LOGGER = LoggerFactory.getLogger("Technical Creation");



	@Override
	public void onInitialize() {
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModBlockEntity.registerBlockEntities();
		ModScreenHandler.registerScreenHandler();
		ModRecipes.register();
	}
}
