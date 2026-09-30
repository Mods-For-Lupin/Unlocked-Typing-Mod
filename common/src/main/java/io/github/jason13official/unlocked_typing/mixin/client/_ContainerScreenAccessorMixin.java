package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ContainerScreenAccessor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(AbstractContainerScreen.class)
public abstract class _ContainerScreenAccessorMixin implements ContainerScreenAccessor {

  @Shadow protected int leftPos;
  @Shadow protected int topPos;
  @Shadow protected int imageWidth;

  @Override
  public int unlocked_typing$getLeftPos() {
    return this.leftPos;
  }

  @Override
  public int unlocked_typing$getTopPos() {
    return this.topPos;
  }

  @Override
  public int unlocked_typing$getImageWidth() {
    return this.imageWidth;
  }
}
