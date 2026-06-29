package io.github.jason13official.unlocked_typing.mixin.client;

import net.minecraft.client.gui.screens.inventory.BookSignScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(BookSignScreen.class)
public class BookSignScreenMixin {

  /// @reason increase book title limit from 15 to 32 characters
  @ModifyConstant(method = "init()V", constant = @Constant(intValue = 15))
  private int unlocked_typing$modifyTitleMaxLength(int value) {
    return 32;
  }
}
