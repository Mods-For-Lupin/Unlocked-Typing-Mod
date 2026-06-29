package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public class FabricBookEditScreenMixin {

  /// fabric has synthetic methods remapped; method_27593 = lambda$new$3 (title char limit predicate)
  @Inject(at = @At("HEAD"), method = "method_27593", cancellable = true)
  private static void unlocked_typing$titleEditStringValidator(String string, CallbackInfoReturnable<Boolean> cir) {
    cir.setReturnValue(string.length() <= 32);
  }
}
