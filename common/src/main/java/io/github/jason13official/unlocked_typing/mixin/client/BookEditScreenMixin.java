package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.BookEditScreenAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.DisplayCacheAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.LineInfoAccessor;
import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.font.TextFieldHelper;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen.DisplayCache;
import net.minecraft.client.gui.screens.inventory.BookEditScreen.LineInfo;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin {

  @Unique
  private static final int UNLOCKED_TYPING$OFFSET = 140;

  @Shadow
  private boolean isSigning;

  @Shadow
  private String title;

  @Shadow
  private Component pageMsg;

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

  @Inject(at = @At("TAIL"), method = "render")
  private void unlocked_typing$render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {

    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) {
      return;
    }

    if (this.isSigning) {
      this.unlocked_typing$renderBookSigningDisplay(guiGraphics);
    } else {
      this.unlocked_typing$renderBookWritingDisplay(guiGraphics);
    }
  }

  @Unique
  private void unlocked_typing$renderBookSigningDisplay(GuiGraphics guiGraphics) {

    BookEditScreen screen = (BookEditScreen) (Object) this;
    ScreenAccessor screenAccessor = (ScreenAccessor) screen;

    int i = (screenAccessor.unlocked_typing$getWidth() - 192) / 2;
    int textColor = DyeColor.LIGHT_GRAY.getTextColor();
    String helperRawText = Component.translatable("unlocked_typing.preformattedTextTitle").getString();
    int titleX = (i + 36 + (114 - 90) / 2) + UNLOCKED_TYPING$OFFSET;

    guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(),
        FormattedCharSequence.forward(helperRawText, Style.EMPTY.withUnderlined(true)),
        titleX, 50 - 11, textColor, false);

    guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(),
        FormattedCharSequence.forward(this.title, Style.EMPTY),
        titleX, 50, textColor, false);

    guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(),
        Component.literal(this.title),
        titleX, 50 + 18, 0xFFFFFFFF, false);
  }

  @Unique
  private void unlocked_typing$renderBookWritingDisplay(GuiGraphics guiGraphics) {

    BookEditScreen screen = (BookEditScreen) (Object) this;
    ScreenAccessor screenAccessor = (ScreenAccessor) screen;
    DisplayCacheAccessor displayCacheAccessor = (DisplayCacheAccessor) ((BookEditScreenAccessor) screen).unlocked_typing$getDisplayCache();

    int i = (screenAccessor.unlocked_typing$getWidth() - 192) / 2;
    int n = screenAccessor.unlocked_typing$getFont().width(this.pageMsg);
    int textColor = DyeColor.LIGHT_GRAY.getTextColor();
    String helperRawText = Component.translatable("unlocked_typing.preformattedTextTitle").getString();

    guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(),
        FormattedCharSequence.forward(helperRawText, Style.EMPTY.withUnderlined(true)),
        (i + 192 - 44) + 27, 18, textColor, false);

    guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(), this.pageMsg, i - n + 192 - 44, 18, 0, false);

    for (LineInfo lineInfo : displayCacheAccessor.unlocked_typing$getLines()) {
      LineInfoAccessor lineInfoAccessor = (LineInfoAccessor) lineInfo;
      FormattedCharSequence seq = FormattedCharSequence.forward(lineInfoAccessor.unlocked_typing$getAsComponent().getString(), Style.EMPTY);
      guiGraphics.drawString(screenAccessor.unlocked_typing$getFont(), seq,
          lineInfoAccessor.unlocked_typing$getX() + UNLOCKED_TYPING$OFFSET,
          lineInfoAccessor.unlocked_typing$getY(), textColor, false);
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
