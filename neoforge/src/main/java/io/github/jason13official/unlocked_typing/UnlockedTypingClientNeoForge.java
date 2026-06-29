package io.github.jason13official.unlocked_typing;

import java.util.function.Consumer;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;

public class UnlockedTypingClientNeoForge {

  public UnlockedTypingClientNeoForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> UnlockedTypingClient.init());
  }
}
