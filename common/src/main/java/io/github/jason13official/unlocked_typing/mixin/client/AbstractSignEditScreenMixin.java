package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.client.gui.screens.inventory.AbstractSignEditScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.block.entity.SignBlockEntity;
import org.joml.Vector3f;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractSignEditScreen.class)
public abstract class AbstractSignEditScreenMixin {

  @Unique
  private static final int UNLOCKED_TYPING$OFFSET = 120;

  @Shadow @Final protected SignBlockEntity sign;
  @Shadow @Final private String[] messages;
//  @Shadow public int width;

  @Shadow
  protected abstract Vector3f getSignTextScale();

  @Shadow
  protected abstract float getSignYOffset();

  @Inject(at = @At("TAIL"), method = "extractRenderState")
  private void unlocked_typing$extractRenderState(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a, CallbackInfo ci) {
    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) return;

    AbstractSignEditScreen self = (AbstractSignEditScreen) (Object) this;

    Font font = Minecraft.getInstance().font;
    String titleString = Component.translatable("unlocked_typing.preformattedTextTitle").getString();
    FormattedCharSequence title = FormattedCharSequence.forward(titleString, Style.EMPTY.withUnderlined(true));
    int textColor = DyeColor.LIGHT_GRAY.getTextColor();
    int yOffset = 4 * this.sign.getTextLineHeight() / 2;
    int previewX = self.width / 2 + UNLOCKED_TYPING$OFFSET;
    int previewY = (int) this.getSignYOffset();

    graphics.text(font, title, previewX - font.width(titleString) / 2, previewY - yOffset - 11, textColor, false);

    for (int i = 0; i < this.messages.length; ++i) {
      String message = this.messages[i];
      if (message != null) {
        if (font.isBidirectional()) {
          message = font.bidirectionalShaping(message);
        }
        FormattedCharSequence seq = FormattedCharSequence.forward(message, Style.EMPTY);
        graphics.text(font, seq, previewX - font.width(message) / 2, previewY + i * this.sign.getTextLineHeight() - yOffset, textColor, false);
      }
    }
  }

  @Inject(at = @At("TAIL"), method = "init()V")
  private void unlocked_typing$init(CallbackInfo ci) {
    Screen self = (Screen) (Object) this;
    ScreenAccessor accessor = (ScreenAccessor) self;
    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(CopySymbolButton.create());
  }
}
