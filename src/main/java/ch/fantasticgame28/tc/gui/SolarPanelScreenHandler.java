package ch.fantasticgame28.tc.gui;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ScreenHandler;

public class SolarPanelScreenHandler extends ScreenHandler {

    public SolarPanelScreenHandler(int syncId) {
        super(ModScreenHandler.SOLAR_PANEL_SCREEN_HANDLER, syncId);

    }



    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
