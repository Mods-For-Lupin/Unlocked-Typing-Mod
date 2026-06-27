package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.LineInfoAccessor;
import net.minecraft.client.gui.screens.inventory.BookEditScreen.LineInfo;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(LineInfo.class)
public class _LineInfoAccessorMixin implements LineInfoAccessor {


  @Shadow
  @Final
  private int x;

  @Shadow
  @Final
  private int y;

  @Shadow
  @Final
  private Component asComponent;

  @Override
  public int unlocked_typing$getX() {

    return this.x;
  }

  @Override
  public int unlocked_typing$getY() {

    return this.y;
  }

  @Override
  public Component unlocked_typing$getAsComponent() {

    return this.asComponent;
  }
}
