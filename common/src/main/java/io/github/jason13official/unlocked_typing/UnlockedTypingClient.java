package io.github.jason13official.unlocked_typing;

import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import io.github.jason13official.unlocked_typing.platform.Services;

public class UnlockedTypingClient {

  public static void init() {
    UnlockedTypingConfig.load(Services.PLATFORM.getConfigDirectory());
  }
}