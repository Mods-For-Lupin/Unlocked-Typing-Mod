package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.util.StringUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StringUtil.class)
public class StringUtilMixin {

  /// @reason casting `'§'` to an int returns `167` or the "ascii codepoint" -> we allow
  @Inject(at = @At("HEAD"), method = "isAllowedChatCharacter", cancellable = true)
  private static void unlocked_typing$isAllowedChatCharacter(char character, CallbackInfoReturnable<Boolean> cir) {
    if (character == (char) 167) cir.setReturnValue(true);
  }
}
