package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import io.github.jason13official.unlocked_typing.impl.client.CopySymbolButton;
import io.github.jason13official.unlocked_typing.impl.client.ToggleDisplayButton;
import io.github.jason13official.unlocked_typing.impl.common.UnlockedTypingConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
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
  private static final int UNLOCKED_TYPING$OFFSET = 100;

  @Shadow @Final private SignBlockEntity sign;
  @Shadow @Final private String[] messages;

  @Shadow
  protected abstract Vector3f getSignTextScale();

  /// Re-establishes sign coordinate transforms because renderSignText's own pushPose/popPose
  /// has already unwound by the time TAIL fires.
  @Inject(at = @At("TAIL"), method = "renderSignText")
  private void unlocked_typing$renderSignText(GuiGraphics guiGraphics, CallbackInfo ci) {
    if (!UnlockedTypingConfig.client().shouldDisplayExamples()) {
      return;
    }

    Font font = Minecraft.getInstance().font;
    String titleString = Component.translatable("unlocked_typing.preformattedTextTitle").getString();
    FormattedCharSequence title = FormattedCharSequence.forward(titleString, Style.EMPTY.withUnderlined(true));
    int textColor = DyeColor.LIGHT_GRAY.getTextColor();
    int yOffset = 4 * this.sign.getTextLineHeight() / 2;

    guiGraphics.pose().pushPose();
    guiGraphics.pose().translate(0f, 0f, 4f);
    Vector3f scale = this.getSignTextScale();
    guiGraphics.pose().scale(scale.x, scale.y, scale.z);

    guiGraphics.drawString(font, title, -font.width(titleString) / 2 + UNLOCKED_TYPING$OFFSET, yOffset - 51, textColor, false);

    for (int i = 0; i < this.messages.length; ++i) {
      String message = this.messages[i];
      if (message != null) {
        if (font.isBidirectional()) {
          message = font.bidirectionalShaping(message);
        }
        FormattedCharSequence seq = FormattedCharSequence.forward(message, Style.EMPTY);
        guiGraphics.drawString(font, seq, -font.width(message) / 2 + UNLOCKED_TYPING$OFFSET, i * this.sign.getTextLineHeight() - yOffset, textColor, false);
      }
    }

    guiGraphics.pose().popPose();
  }

  @Inject(at = @At("TAIL"), method = "init()V")
  private void unlocked_typing$init(CallbackInfo ci) {
    Screen self = (Screen) (Object) this;
    ScreenAccessor accessor = (ScreenAccessor) self;
    accessor.unlocked_typing$addRenderableWidget(ToggleDisplayButton.create());
    accessor.unlocked_typing$addRenderableWidget(new CopySymbolButton());
  }
}
