package io.github.jason13official.unlocked_typing;

import net.fabricmc.api.ClientModInitializer;

public class UnlockedTypingClientFabric implements ClientModInitializer {

  @Override
  public void onInitializeClient() {

    UnlockedTypingClient.init();
  }
}
