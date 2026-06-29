package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public class NeoForgeBookEditScreenMixin {

  /// NeoForge 1.21.1 uses official Mojang mappings, so lambda$new$3 is stable in both dev and production
  @Inject(at = @At("HEAD"), method = "lambda$new$3", cancellable = true)
  private static void unlocked_typing$titleEditStringValidator(String string, CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(string.length() <= 32);
  }
}
