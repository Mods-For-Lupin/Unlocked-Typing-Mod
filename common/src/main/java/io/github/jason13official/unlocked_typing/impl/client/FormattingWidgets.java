package io.github.jason13official.unlocked_typing.impl.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;

public final class FormattingWidgets {

  public static final int ROW_BUTTON_SIZE = 20;
  private static final int ROW_TOGGLE_WIDTH = 96;
  private static final int ROW_GAP = 6;
  public static final int ROW_SPACING = ROW_BUTTON_SIZE + 4;

  public static void addTo(ScreenAccessor screen, int copyX, int helpX, int y, int size) {
    screen.unlocked_typing$addRenderableWidget(new FormattingButton(copyX, y, size, "§",
        tooltip("unlocked_typing.button.copySymbol"),
        button -> ClipboardHelper.copyToClipboard("§")));
    addHelpTo(screen, helpX, y, size);
  }

  public static void addPlainCopyTo(ScreenAccessor screen, int x, int y, int width, int height) {
    screen.unlocked_typing$addRenderableWidget(new PlainFormattingButton(x, y, width, height, "§",
        tooltip("unlocked_typing.button.copySymbol"),
        button -> ClipboardHelper.copyToClipboard("§")));
  }

  public static void addRow(ScreenAccessor screen, int centerX, int y) {
    int x = centerX - (ROW_BUTTON_SIZE + ROW_GAP + ROW_TOGGLE_WIDTH + ROW_GAP + ROW_BUTTON_SIZE) / 2;
    screen.unlocked_typing$addRenderableWidget(new FormattingButton(x, y, ROW_BUTTON_SIZE, "§",
        tooltip("unlocked_typing.button.copySymbol"),
        button -> ClipboardHelper.copyToClipboard("§")));
    x += ROW_BUTTON_SIZE + ROW_GAP;
    screen.unlocked_typing$addRenderableWidget(new RawTextToggleButton(x, y, ROW_TOGGLE_WIDTH, ROW_BUTTON_SIZE,
        tooltip("unlocked_typing.button.rawText.title")));
    x += ROW_TOGGLE_WIDTH + ROW_GAP;
    addHelpTo(screen, x, y, ROW_BUTTON_SIZE);
  }

  public static void moveButtons(Screen screen, int fromY, int toY) {
    for (GuiEventListener listener : screen.children()) {
      if (listener instanceof AbstractWidget widget && !(widget instanceof FormattingButton) && widget.getY() == fromY) {
        widget.setY(toY);
      }
    }
  }

  private static void addHelpTo(ScreenAccessor screen, int helpX, int y, int size) {
    screen.unlocked_typing$addRenderableWidget(new FormattingButton(helpX, y, size, "?",
        tooltip("unlocked_typing.button.formattingCodes"),
        button -> FormattingCodesPanel.toggle()) {
      @Override
      public void renderWidget(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick) {
        super.renderWidget(guiGraphics, mouseX, mouseY, partialTick);
        if (FormattingCodesPanel.isOpen()) {
          FormattingCodesPanel.render(guiGraphics, Minecraft.getInstance().font, this);
        }
      }
    });
  }

  private static Component tooltip(String key) {
    return Component.translatable(key)
        .append("\n")
        .append(Component.translatable(key + ".description").withStyle(ChatFormatting.GRAY));
  }
}
