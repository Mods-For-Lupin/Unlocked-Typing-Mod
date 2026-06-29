package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import java.util.List;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookEditScreen.class)
public abstract class BookEditScreenMixin {

  @Shadow private int currentPage;
  @Shadow @Final private List<String> pages;

  @Inject(at = @At("TAIL"), method = "init()V")
  private void unlocked_typing$init(CallbackInfo ci) {
    Screen self = (Screen) (Object) this;
    ScreenAccessor accessor = (ScreenAccessor) self;
    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(CopySymbolButton.create());
  }

  @Inject(at = @At("TAIL"), method = "extractRenderState")
  private void unlocked_typing$extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) return;

    Font font = Minecraft.getInstance().font;
    String pageText = this.pages.isEmpty() ? "" : this.pages.get(this.currentPage);

    String titleString = Component.translatable("unlocked_typing.preformattedTextTitle").getString();
    FormattedCharSequence title = FormattedCharSequence.forward(titleString, Style.EMPTY.withUnderlined(true));

    int previewX = (((ScreenAccessor)(Object)this).unlocked_typing$getWidth() + 192) / 2 + 10;
    int previewY = 10;

    graphics.text(font, title, previewX, previewY, 0xFFFFFFFF, false);
    previewY += 11;

    for (String line : pageText.split("\n", -1)) {
      graphics.text(font, FormattedCharSequence.forward(line, Style.EMPTY), previewX, previewY, 0xFFFFFFFF, false);
      previewY += 9;
    }
  }
}
