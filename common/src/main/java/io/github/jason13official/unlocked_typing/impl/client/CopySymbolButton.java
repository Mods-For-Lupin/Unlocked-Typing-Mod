package io.github.jason13official.unlocked_typing.impl.client;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.PlainTextButton;
import net.minecraft.client.gui.components.WidgetSprites;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;

public class CopySymbolButton extends PlainTextButton {

  private static final WidgetSprites BUTTON_SPRITES = new WidgetSprites(
      ResourceLocation.withDefaultNamespace("widget/button"),
      ResourceLocation.withDefaultNamespace("widget/button_disabled"),
      ResourceLocation.withDefaultNamespace("widget/button_highlighted")
  );

  public CopySymbolButton() {
    super(80, 0, 80, 16, Component.literal("COPY SYMBOL").withStyle(Style.EMPTY), button -> ClipboardHelper.copyToClipboard("§"), Minecraft.getInstance().font);
  }

  @Override
  public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
    Minecraft minecraft = Minecraft.getInstance();
    guiGraphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
    RenderSystem.enableBlend();
    RenderSystem.enableDepthTest();
    guiGraphics.blitSprite(BUTTON_SPRITES.get(this.active, this.isHoveredOrFocused()), this.getX(), this.getY(), this.getWidth(), this.getHeight());
    guiGraphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
    int i = this.active ? 16777215 : 10526880;
    this.renderString(guiGraphics, minecraft.font, i | Mth.ceil(this.alpha * 255.0F) << 24);
  }
}
