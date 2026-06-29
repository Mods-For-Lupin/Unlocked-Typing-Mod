package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.util.StringUtil;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(StringUtil.class)
public class StringUtilMixin {

  /// @reason param is int (not char) in 26.1.2; 167 = '§' codepoint
  @Inject(at = @At("HEAD"), method = "isAllowedChatCharacter", cancellable = true)
  private static void unlocked_typing$isAllowedChatCharacter(int character, CallbackInfoReturnable<Boolean> cir) {
    if (character == 167) cir.setReturnValue(true);
  }
}
