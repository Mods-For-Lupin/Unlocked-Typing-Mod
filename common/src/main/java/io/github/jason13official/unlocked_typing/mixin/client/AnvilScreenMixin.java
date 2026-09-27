package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ContainerScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.FormattingWidgets;
import io.github.jason13official.unlocked_typing.platform.Services;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

  @Unique
  private static final int UNLOCKED_TYPING$BUTTON_SIZE = 14;

  @Inject(at = @At("TAIL"), method = "subInit")
  private void unlocked_typing$init(CallbackInfo ci) {
    ContainerScreenAccessor container = (ContainerScreenAccessor) this;
    int size = UNLOCKED_TYPING$BUTTON_SIZE;
    int right = container.unlocked_typing$getLeftPos() + container.unlocked_typing$getImageWidth() - 7;
    if (Services.PLATFORM.isModLoaded("easyanvils")) {
      right -= 14;
    }
    int helpX = right - size;
    int copyX = helpX - size - 2;
    FormattingWidgets.addTo((ScreenAccessor) this, copyX, helpX, container.unlocked_typing$getTopPos() + 4, size);
  }
}
