package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.impl.client.FormattingExamplesHelper;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AnvilScreen;
import net.minecraft.client.gui.screens.inventory.ItemCombinerScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/// Injects at the tail of ItemCombinerScreen.render() so examples draw after all container
/// UI (slots, labels, fg) has rendered — Screen.render() TAIL fires too early for container screens.
@Mixin(ItemCombinerScreen.class)
public abstract class AnvilScreenRenderMixin {

  @Inject(at = @At("TAIL"), method = "render")
  private void unlocked_typing$render(GuiGraphics guiGraphics, int mouseX, int mouseY, float partialTick, CallbackInfo ci) {
    if (!(((Object) this) instanceof AnvilScreen)) return;
    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) return;
    FormattingExamplesHelper.renderFormattingExamples(guiGraphics, Minecraft.getInstance().font);
  }
}
