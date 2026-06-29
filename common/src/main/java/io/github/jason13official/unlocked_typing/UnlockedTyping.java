package io.github.jason13official.unlocked_typing;

import net.minecraft.resources.Identifier;

public class UnlockedTyping {

  public static void init() {
  }

  public static Identifier identifier(final String path) {
    return Identifier.fromNamespaceAndPath(Constants.MOD_ID, path);
  }
}