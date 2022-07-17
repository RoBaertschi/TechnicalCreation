package ch.fantasticgame28.tc.gui;

import ch.fantasticgame28.tc.TechnicalCreation;
import ch.fantasticgame28.tc.blockentity.custom.SolarPanelBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.ArrayPropertyDelegate;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;

public class SolarPanelScreenHandler extends ScreenHandler {

    private final PropertyDelegate propertyDelegate;

    public SolarPanelScreenHandler(int syncId) {
        this(syncId, new ArrayPropertyDelegate(2));
    }

    public SolarPanelScreenHandler(int syncId, PropertyDelegate delegate) {
        super(ModScreenHandler.SOLAR_PANEL_SCREEN_HANDLER, syncId);
        propertyDelegate = delegate;

        addProperties(delegate);
    }

    public int getProgress() {
        int energyAmount = this.propertyDelegate.get(0);
        int maxEnergy = this.propertyDelegate.get(1);

        return energyAmount != 0 ? (int)(((float)energyAmount / (float) maxEnergy) * 100f * (75f / 100f)) : 0;
    }


    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }
}
