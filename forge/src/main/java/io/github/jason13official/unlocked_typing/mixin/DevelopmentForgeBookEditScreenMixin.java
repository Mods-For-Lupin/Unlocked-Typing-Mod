package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public class DevelopmentForgeBookEditScreenMixin {

  @Inject(at = @At("HEAD"), method = "lambda$new$3", cancellable = true)
  private static void unlocked_typing$titleEditStringValidator(String p_98170_, CallbackInfoReturnable<Boolean> cir) {

    cir.setReturnValue(p_98170_.length() <= 32);
  }
}
