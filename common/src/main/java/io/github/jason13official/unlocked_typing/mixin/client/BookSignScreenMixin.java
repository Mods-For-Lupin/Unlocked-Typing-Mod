package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.BookSignScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.DyeColor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BookSignScreen.class)
public class BookSignScreenMixin {

  @Shadow private EditBox titleBox;

  @ModifyConstant(method = "init()V", constant = @Constant(intValue = 15))
  private int unlocked_typing$modifyTitleMaxLength(int value) {
    return 32;
  }

  @Inject(at = @At("TAIL"), method = "init()V")
  private void unlocked_typing$init(CallbackInfo ci) {
    ScreenAccessor accessor = (ScreenAccessor)(Object)this;
    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(CopySymbolButton.create());
  }

  @Inject(at = @At("TAIL"), method = "extractRenderState")
  private void unlocked_typing$extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if (!UnlockedTypingConfig.client().shouldDisplayExamples() || this.titleBox == null) return;

    String title = this.titleBox.getValue();
    Font font = Minecraft.getInstance().font;
    int screenWidth = ((ScreenAccessor)(Object)this).unlocked_typing$getWidth();
    int previewX = (screenWidth + 192) / 2 + 10;
    int textColor = DyeColor.LIGHT_GRAY.getTextColor();

    String labelStr = Component.translatable("unlocked_typing.preformattedTextTitle").getString();
    graphics.text(font, FormattedCharSequence.forward(labelStr, Style.EMPTY.withUnderlined(true)), previewX, 39, textColor, false);
    graphics.text(font, FormattedCharSequence.forward(title, Style.EMPTY), previewX, 50, textColor, false);
    graphics.text(font, Component.literal(title), previewX, 68, 0xFFFFFFFF, false);
  }
}
