package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin {

  @Shadow
  private boolean isSigning;

  @Shadow
  private String title;

  @Shadow
  @Final
  private TextFieldHelper titleEdit;

  /// Allows pasting into the book title field during signing
  @Inject(at = @At("HEAD"), method = "titleKeyPressed", cancellable = true)
  private void unlocked_typing$titleKeyPressed(int keyCode, int scanCode, int modifiers, CallbackInfoReturnable<Boolean> cir) {

    if (this.isSigning && Screen.isPaste(keyCode) && this.title.length() < 30) {
      this.titleEdit.insertText(TextFieldHelper.getClipboardContents(Minecraft.getInstance()));
      cir.setReturnValue(true);
    }
  }

  @Inject(at = @At("TAIL"), method = "init()V")
  private void unlocked_typing$init(CallbackInfo ci) {

    Screen self = (Screen) (Object) this;
    ScreenAccessor accessor = (ScreenAccessor) self;

    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(new CopySymbolButton());
  }
}
