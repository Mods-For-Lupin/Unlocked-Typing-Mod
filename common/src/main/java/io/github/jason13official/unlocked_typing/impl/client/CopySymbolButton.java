package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.client.gui.components.PlainTextButton;

public class CopySymbolButton extends PlainTextButton {

  public CopySymbolButton() {
    super(80, 0, 80, 16, Component.literal("COPY SYMBOL").withStyle(Style.EMPTY), button -> ClipboardHelper.copyToClipboard("§"), Minecraft.getInstance().font);
  }
}
