package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

public class FormattingButton extends Button {

  private final String label;

  public FormattingButton(int x, int y, int size, String label, Component tooltip, OnPress onPress) {
    super(x, y, size, size, tooltip, onPress, DEFAULT_NARRATION);
    this.label = label;
    this.setTooltip(Tooltip.create(tooltip));
  }

  @Override
  public boolean mouseClicked(double mouseX, double mouseY, int button) {
    if (this.active && this.visible && this.isValidClickButton(button) && this.clicked(mouseX, mouseY)) {
      this.playDownSound(Minecraft.getInstance().getSoundManager());
      this.onPress();
    }
    return false;
  }

  @Override
  public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
    return false;
  }

  @Override
  public void renderString(GuiGraphics guiGraphics, Font font, int color) {
    FormattedCharSequence text = FormattedCharSequence.forward(this.label, Style.EMPTY);
    int x = this.getX() + (this.getWidth() - font.width(text) + 1) / 2;
    int y = this.getY() + (this.getHeight() - 8) / 2;
    guiGraphics.drawString(font, text, x, y, color);
  }
}
