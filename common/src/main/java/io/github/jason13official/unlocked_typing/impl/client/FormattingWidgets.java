package io.github.jason13official.unlocked_typing.impl.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;

public final class FormattingWidgets {

  public static void addTo(ScreenAccessor screen, int copyX, int helpX, int y, int size) {
    screen.unlocked_typing$addRenderableWidget(new FormattingButton(copyX, y, size, "§",
        Component.translatable("unlocked_typing.button.copySymbol"),
        button -> ClipboardHelper.copyToClipboard("§")));

    screen.unlocked_typing$addRenderableWidget(new FormattingButton(helpX, y, size, "?",
        Component.translatable("unlocked_typing.button.formattingCodes"),
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
}
