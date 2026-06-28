package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public class ProductionForgeBookEditScreenMixin {

  /// @reason only valid in a production environment, and the `method` parameter fails in development
  @SuppressWarnings("all")
  @Inject(at = @At("HEAD"), method = "m_98169_", cancellable = true)
  private static void unlocked_typing$titleEditStringValidator(String string, CallbackInfoReturnable<Boolean> cir) {

    cir.setReturnValue(string.length() <= 32);
  }
}
