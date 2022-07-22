package ch.fantasticgame28.tc.gui;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.blockentity.ModBlockEntity;
import ch.fantasticgame28.tc.blockentity.custom.SolarPanelBlockEntity;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


@SuppressWarnings("deprecation")
public class ModScreenHandler {

    public static final ScreenHandlerType<SolarPanelScreenHandler> SOLAR_PANEL_SCREEN_HANDLER;
    public static final ScreenHandlerType<HeaterScreenHandler> HEATER_SCREEN_HANDLER;

    static {
        SOLAR_PANEL_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(TechnicalCreation.MOD_ID, "solar_panel_screen_handler"),
                (int syncId, PlayerInventory inventory) -> new SolarPanelScreenHandler(syncId));
        HEATER_SCREEN_HANDLER = ScreenHandlerRegistry.registerSimple(new Identifier(TechnicalCreation.MOD_ID, "heater_screen_handler"),
                HeaterScreenHandler::new);
    }


    public static void registerScreenHandler() {
        TechnicalCreation.LOGGER.info("Registering Screen Handlers");
        // SOLAR_PANEL_SCREEN_HANDLER = Registry.register(Registry.SCREEN_HANDLER, new Identifier(TechnicalCreation.MOD_ID, "solar_panel"), new SolarPanelScreenHandler());
    }
}
