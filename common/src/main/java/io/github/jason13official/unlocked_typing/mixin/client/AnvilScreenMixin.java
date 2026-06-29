package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.FormattingExamplesHelper;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AnvilScreen.class)
public class AnvilScreenMixin {

  @Inject(at = @At("TAIL"), method = "subInit")
  private void unlocked_typing$init(CallbackInfo ci) {
    Screen self = (Screen) (Object) this;
    ScreenAccessor accessor = (ScreenAccessor) self;
    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(CopySymbolButton.create());
  }

  @Inject(at = @At("TAIL"), method = "extractBackground")
  private void unlocked_typing$extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) return;
    FormattingExamplesHelper.renderFormattingExamples(graphics, Minecraft.getInstance().font);
  }
}
