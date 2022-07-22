package ch.fantasticgame28.tc.gui;

import ch.fantasticgame28.tc.TechnicalCreation;
import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.gui.screen.ingame.HandledScreen;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class HeaterScreen extends HandledScreen<HeaterScreenHandler> {
    private static final Identifier TEXTURE =
            new Identifier(TechnicalCreation.MOD_ID, "textures/gui/heater_screen.png");

    public HeaterScreen(HeaterScreenHandler handler, PlayerInventory inventory, Text title) {
        super(handler, inventory, title);
    }

    @Override
    protected void init() {
        super.init();
        titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2;
    }

    @Override
    protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        RenderSystem.setShaderTexture(0, TEXTURE);
        int x = (width - backgroundWidth) / 2;
        int y = (height - backgroundHeight) / 2;
        drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);

        if(handler.isCrafting()) {
            drawTexture(matrices, x + 84, y + 22, 176, 14, handler.getScaledProgress(), 36);
        }

        drawTexture(matrices, x + 18, y + 33 + 14 - handler.getScaledEnergy(), 176,
                14 - handler.getScaledEnergy(), 14, handler.getScaledEnergy());

    }
}
