package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.BookEditScreenAccessor;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen.DisplayCache;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(BookEditScreen.class)
public abstract class _BookEditScreenAccessorMixin implements BookEditScreenAccessor {

  @Shadow
  protected abstract DisplayCache getDisplayCache();

  @Override
  public DisplayCache unlocked_typing$getDisplayCache() {
    return this.getDisplayCache();
  }
}
