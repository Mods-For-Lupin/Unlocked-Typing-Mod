package io.github.jason13official.unlocked_typing;

import java.util.function.Consumer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class UnlockedTypingClientForge {

  public UnlockedTypingClientForge(final IEventBus modEventBus) {

    modEventBus.addListener((Consumer<FMLClientSetupEvent>) event -> UnlockedTypingClient.init());
  }
}
