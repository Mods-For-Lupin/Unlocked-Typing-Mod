package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.impl.client.FormattingExamplesHelper;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import java.util.function.Predicate;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.client.gui.screens.inventory.BookEditScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Screen.class)
public abstract class ScreenMixin {

  @Unique
  private static final Predicate<Screen> UNLOCKED_TYPING$VALID_DISPLAY = self
      -> self instanceof BookEditScreen
      || self instanceof AbstractSignEditScreen;

  @Shadow
  protected Font font;

  @Shadow
  protected abstract <T extends GuiEventListener & Renderable & NarratableEntry> T addRenderableWidget(T widget);

  @Inject(at = @At("TAIL"), method = "render")
  private void unlocked_typing$render$renderHelperText(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
    Screen self = (Screen) (Object) this;
    if (UnlockedTypingConfig.client().shouldDisplayExamples() && UNLOCKED_TYPING$VALID_DISPLAY.test(self)) {
      FormattingExamplesHelper.renderFormattingExamples(guiGraphics, this.font);
    }
  }
}
