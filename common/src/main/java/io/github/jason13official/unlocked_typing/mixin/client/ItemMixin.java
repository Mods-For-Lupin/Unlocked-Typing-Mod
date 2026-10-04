package io.github.jason13official.unlocked_typing.mixin.client;

import net.minecraft.world.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Item.class)
public class ItemMixin {

  @Inject(at = @At("TAIL"), method = "<clinit>")
  private static void ut$clinit(CallbackInfo ci) {

    System.out.println("Item.class loaded/loading?");
  }

  @Inject(at = @At("HEAD"), method = "canBeDepleted")
  private void ut$canBeDepleted(CallbackInfoReturnable<Boolean> cir) {

    System.out.println("inject canBeDepleted in Item.class worked.");
  }
}
