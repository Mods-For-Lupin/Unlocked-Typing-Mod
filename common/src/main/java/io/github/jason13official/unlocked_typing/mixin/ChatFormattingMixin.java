package io.github.jason13official.unlocked_typing.mixin;

import net.minecraft.ChatFormatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ChatFormatting.class)
public class ChatFormattingMixin {

  /// @reason returns the original text without stripping formatting such as
  /// §0, §9, §a, §f, §k, §o, §r
  @Inject(at = @At("HEAD"), method = "stripFormatting", cancellable = true)
  private static void unlocked_typing$stripFormatting(String text, CallbackInfoReturnable<String> cir) {

    // null safety, just return original text if it exists
    if (text != null) cir.setReturnValue(text);
  }
}
