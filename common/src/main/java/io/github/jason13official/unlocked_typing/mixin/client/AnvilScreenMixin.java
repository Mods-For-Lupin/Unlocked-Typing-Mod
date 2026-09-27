package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.AnvilScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ContainerScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.FormattingWidgets;
import io.github.jason13official.unlocked_typing.platform.Services;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin implements AnvilScreenAccessor {

  @Unique
  private static final int UNLOCKED_TYPING$BUTTON_SIZE = 14;

  @Unique
  private static final int UNLOCKED_TYPING$EASY_ANVILS_TITLE_Y = 8;

  @Unique
  private static final int UNLOCKED_TYPING$EASY_ANVILS_GAP = 2;

  @Override
  public void unlocked_typing$initFormattingWidgets() {
    ContainerScreenAccessor container = (ContainerScreenAccessor) this;
    ScreenAccessor screen = (ScreenAccessor) this;
    int right = container.unlocked_typing$getLeftPos() + container.unlocked_typing$getImageWidth() - 7;
    int top = container.unlocked_typing$getTopPos();

    if (Services.PLATFORM.isModLoaded("easyanvils")) {
      Font font = screen.unlocked_typing$getFont();
      int width = font.width(FormattedCharSequence.forward("§", Style.EMPTY)) * 2;
      int x = right - font.width("?") * 2 - UNLOCKED_TYPING$EASY_ANVILS_GAP - width;
      FormattingWidgets.addPlainCopyTo(screen, x, top + UNLOCKED_TYPING$EASY_ANVILS_TITLE_Y, width, font.lineHeight);
      return;
    }

    int size = UNLOCKED_TYPING$BUTTON_SIZE;
    int helpX = right - size;
    int copyX = helpX - size - 2;
    FormattingWidgets.addTo(screen, copyX, helpX, top + 4, size);
  }
}
