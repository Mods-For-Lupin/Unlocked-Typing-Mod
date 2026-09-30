package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.AnvilScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ContainerScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.FormattingWidgets;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin implements AnvilScreenAccessor {

  @Unique
  private static final int UNLOCKED_TYPING$BUTTON_SIZE = 14;

  @Override
  public void unlocked_typing$initFormattingWidgets() {
    ContainerScreenAccessor container = (ContainerScreenAccessor) this;
    int size = UNLOCKED_TYPING$BUTTON_SIZE;
    int right = container.unlocked_typing$getLeftPos() + container.unlocked_typing$getImageWidth() - 7;
    int helpX = right - size;
    int copyX = helpX - size - 2;
    FormattingWidgets.addTo((ScreenAccessor) this, copyX, helpX, container.unlocked_typing$getTopPos() + 4, size);
  }
}
