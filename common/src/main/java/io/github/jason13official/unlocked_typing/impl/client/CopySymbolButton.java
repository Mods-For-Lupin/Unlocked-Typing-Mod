package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.client.gui.components.Button;
import net.minecraft.network.chat.Component;

public class CopySymbolButton {

  public static Button create() {
    return Button.builder(Component.literal("Copy §"), button -> ClipboardHelper.copyToClipboard("§"))
        .bounds(86, 4, 80, 16)
        .build();
  }

  private CopySymbolButton() {}
}
