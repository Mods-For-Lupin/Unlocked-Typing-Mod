package io.github.jason13official.unlocked_typing;

import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import io.github.jason13official.unlocked_typing.platform.Services;
import net.minecraft.resources.ResourceLocation;


public class UnlockedTyping {

  public static void init() {

    UnlockedTypingConfig.load(Services.PLATFORM.getConfigDirectory());
  }

  public static ResourceLocation identifier(final String path) {

    return new ResourceLocation(Constants.MOD_ID, path);
  }
}