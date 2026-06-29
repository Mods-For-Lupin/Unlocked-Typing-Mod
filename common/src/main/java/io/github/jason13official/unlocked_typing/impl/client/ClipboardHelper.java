package io.github.jason13official.unlocked_typing.impl.client;

import net.minecraft.client.Minecraft;

public class ClipboardHelper {

  public static void copyToClipboard(String value) {
    Minecraft.getInstance().keyboardHandler.setClipboard(value);
  }
}
