package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.Tooltip;
import net.minecraft.client.input.KeyEvent;
import net.minecraft.client.input.MouseButtonEvent;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.ARGB;
import net.minecraft.util.FormattedCharSequence;

public class FormattingButton extends Button {

  private final String label;

  public FormattingButton(int x, int y, int size, String label, Component tooltip, OnPress onPress) {
    this(x, y, size, size, label, tooltip, onPress);
  }

  public FormattingButton(int x, int y, int width, int height, String label, Component tooltip, OnPress onPress) {
    super(x, y, width, height, tooltip, onPress, DEFAULT_NARRATION);
    this.label = label;
    this.setTooltip(Tooltip.create(tooltip));
  }

  @Override
  public boolean mouseClicked(MouseButtonEvent event, boolean doubleClick) {
    if (this.isActive() && this.isValidClickButton(event.buttonInfo()) && this.isMouseOver(event.x(), event.y())) {
      this.playDownSound(Minecraft.getInstance().getSoundManager());
      this.onPress(event);
    }
    return false;
  }

  @Override
  public boolean keyPressed(KeyEvent event) {
    return false;
  }

  protected FormattedCharSequence getLabel() {
    return FormattedCharSequence.forward(this.label, Style.EMPTY);
  }

  @Override
  protected void extractContents(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
    this.extractDefaultSprite(graphics);
    Font font = Minecraft.getInstance().font;
    FormattedCharSequence text = this.getLabel();
    int x = this.getX() + (this.getWidth() - font.width(text) + 1) / 2;
    int y = this.getY() + (this.getHeight() - 8) / 2;
    graphics.text(font, text, x, y, this.active ? ARGB.white(this.alpha) : 0xFFA0A0A0);
  }
}
