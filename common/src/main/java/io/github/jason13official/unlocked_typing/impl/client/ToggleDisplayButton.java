package io.github.jason13official.unlocked_typing.impl.client;

import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import java.util.Locale;
import net.minecraft.client.gui.components.CycleButton;
import net.minecraft.network.chat.Component;

public class ToggleDisplayButton {

  public static CycleButton<Boolean> create() {
    return new CycleButton.Builder<Boolean>(bool -> Component.literal(String.valueOf(bool).toUpperCase(Locale.ROOT)))
        .withInitialValue(UnlockedTypingConfig.client().shouldDisplayExamples())
        .withValues(true, false)
        .create(0, 0, 80, 16, Component.literal("Display"), (button, bool) -> {
          UnlockedTypingConfig.client().setDisplayExamples(bool);
          UnlockedTypingConfig.overwriteClient();
        });
  }

  private ToggleDisplayButton() {}
}
