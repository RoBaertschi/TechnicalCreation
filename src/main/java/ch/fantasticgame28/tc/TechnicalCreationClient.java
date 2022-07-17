package ch.fantasticgame28.tc;

import ch.fantasticgame28.tc.gui.ModScreenHandler;
import ch.fantasticgame28.tc.gui.SolarPanelScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;

public class TechnicalCreationClient implements ClientModInitializer {
    @SuppressWarnings("deprecation")
    @Override
    public void onInitializeClient() {
        ScreenRegistry.register(ModScreenHandler.SOLAR_PANEL_SCREEN_HANDLER, SolarPanelScreen::new);
    }
}
