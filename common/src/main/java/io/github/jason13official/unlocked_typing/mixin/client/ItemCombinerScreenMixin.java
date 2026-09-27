package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.AnvilScreenAccessor;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ItemCombinerScreen.class)
public abstract class ItemCombinerScreenMixin {

  @Inject(at = @At("TAIL"), method = "init")
  private void unlocked_typing$init(CallbackInfo ci) {
    if ((Object) this instanceof AnvilScreen) {
      ((AnvilScreenAccessor) this).unlocked_typing$initFormattingWidgets();
    }
  }
}
