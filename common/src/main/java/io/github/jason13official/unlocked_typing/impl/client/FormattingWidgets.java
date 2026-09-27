package io.github.jason13official.unlocked_typing.impl.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

public final class FormattingWidgets {

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
