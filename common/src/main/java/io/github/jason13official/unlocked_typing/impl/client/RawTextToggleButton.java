package io.github.jason13official.unlocked_typing.impl.client;

import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.network.chat.CommonComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

public class RawTextToggleButton extends FormattingButton {

  public RawTextToggleButton(int x, int y, int width, int height, Component tooltip) {
    super(x, y, width, height, "", tooltip, button -> {
      UnlockedTypingConfig.Client client = UnlockedTypingConfig.client();
      client.setDisplayRawTextPreview(!client.shouldDisplayRawTextPreview());
      UnlockedTypingConfig.overwriteClient();
    });
  }

  @Override
  protected FormattedCharSequence getLabel() {
    Component state = UnlockedTypingConfig.client().shouldDisplayRawTextPreview() ? CommonComponents.OPTION_ON : CommonComponents.OPTION_OFF;
    return FormattedCharSequence.forward(Component.translatable("unlocked_typing.button.rawText", state).getString(), Style.EMPTY);
  }
}
