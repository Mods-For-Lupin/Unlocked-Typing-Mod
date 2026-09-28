package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;

public class PlainFormattingButton extends FormattingButton {

  private static final int TEXT_COLOR = 0xFF404040;
  private static final int HOVERED_TEXT_COLOR = 0xFF000000 | ChatFormatting.YELLOW.getColor();

  public PlainFormattingButton(int x, int y, int width, int height, String label, Component tooltip, OnPress onPress) {
    super(x, y, width, height, label, tooltip, onPress);
  }

  @Override
  protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    Font font = Minecraft.getInstance().font;
    FormattedCharSequence text = this.getLabel();
    int x = this.getX() + (this.getWidth() - font.width(text)) / 2;
    int y = this.getY() + (this.getHeight() - 9) / 2;
    graphics.text(font, text, x, y, this.isHoveredOrFocused() ? HOVERED_TEXT_COLOR : TEXT_COLOR, false);
  }
}
