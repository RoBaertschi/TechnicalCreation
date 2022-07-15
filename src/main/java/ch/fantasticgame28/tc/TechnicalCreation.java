package ch.fantasticgame28.tc;

import ch.fantasticgame28.tc.block.ModBlocks;
import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import ch.fantasticgame28.tc.gui.ModScreenHandler;
import ch.fantasticgame28.tc.gui.SolarPanelScreenHandler;
import ch.fantasticgame28.tc.item.ModItems;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TechnicalCreation implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final String MOD_ID = "tc";
	public static final Logger LOGGER = LoggerFactory.getLogger("Technical Creation");



	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModBlockEntity.registerBlockEntities();
		ModBlocks.registerBlocks();
		ModItems.registerItems();
		ModBlockEntity.registerBlockEntities();
		ModScreenHandler.registerScreenHandler();
		LOGGER.info("Hello Fabric world!");
	}
}
