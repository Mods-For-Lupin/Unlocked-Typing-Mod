package io.github.jason13official.unlocked_typing.impl.client;

import java.util.List;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractWidget;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import net.minecraft.util.FormattedCharSequence;

public final class FormattingCodesPanel {

  private static final List<ChatFormatting> LEFT_COLUMN = List.of(
      ChatFormatting.BLACK, ChatFormatting.DARK_BLUE, ChatFormatting.DARK_GREEN, ChatFormatting.DARK_AQUA,
      ChatFormatting.DARK_RED, ChatFormatting.DARK_PURPLE, ChatFormatting.GOLD, ChatFormatting.GRAY,
      ChatFormatting.DARK_GRAY, ChatFormatting.BLUE, ChatFormatting.GREEN);
  private static final List<ChatFormatting> RIGHT_COLORS = List.of(
      ChatFormatting.AQUA, ChatFormatting.RED, ChatFormatting.LIGHT_PURPLE, ChatFormatting.YELLOW, ChatFormatting.WHITE);
  private static final List<ChatFormatting> RIGHT_STYLES = List.of(
      ChatFormatting.OBFUSCATED, ChatFormatting.BOLD, ChatFormatting.STRIKETHROUGH,
      ChatFormatting.UNDERLINE, ChatFormatting.ITALIC, ChatFormatting.RESET);

  private static final int PADDING = 6;
  private static final int ROW_HEIGHT = 11;
  private static final int SWATCH_SIZE = 7;
  private static final int GAP = 4;
  private static final int COLUMN_GAP = 13;
  private static final int SEPARATOR_HEIGHT = 7;
  private static final int SCREEN_MARGIN = 2;

  private static final int BACKGROUND_COLOR = 0xF0121218;
  private static final int BORDER_COLOR = 0xFF4C5470;
  private static final int DIVIDER_COLOR = 0xFF363A48;
  private static final int SWATCH_BORDER_COLOR = 0x40FFFFFF;
  private static final int STYLE_SWATCH_COLOR = 0xFF6B6B6B;
  private static final int CODE_COLOR = 0xFFA0A0A0;
  private static final int LABEL_COLOR = 0xFFE0E0E0;
  private static final int MUTED_LABEL_COLOR = 0xFFA0A0A0;

  private static boolean open = false;

  public static boolean isOpen() {
    return open;
  }

  public static void toggle() {
    open = !open;
  }

  public static void render(GuiGraphics guiGraphics, Font font, AbstractWidget anchor) {
    int codeWidth = 0;
    for (ChatFormatting formatting : ChatFormatting.values()) {
      codeWidth = Math.max(codeWidth, font.width(code(formatting)));
    }
    int leftWidth = columnWidth(font, codeWidth, LEFT_COLUMN);
    int rightWidth = Math.max(columnWidth(font, codeWidth, RIGHT_COLORS), columnWidth(font, codeWidth, RIGHT_STYLES));

    int width = PADDING + leftWidth + COLUMN_GAP + rightWidth + PADDING;
    int rightRows = RIGHT_COLORS.size() + RIGHT_STYLES.size();
    int contentHeight = Math.max(LEFT_COLUMN.size() * ROW_HEIGHT, rightRows * ROW_HEIGHT + SEPARATOR_HEIGHT) - (ROW_HEIGHT - 9);
    int height = PADDING + contentHeight + PADDING;

    int x = anchor.getX() + anchor.getWidth() + GAP;
    if (x + width > guiGraphics.guiWidth() - SCREEN_MARGIN) {
      x = anchor.getX() - GAP - width;
    }
    x = clamp(x, SCREEN_MARGIN, guiGraphics.guiWidth() - SCREEN_MARGIN - width);
    int y = clamp(anchor.getY(), SCREEN_MARGIN, guiGraphics.guiHeight() - SCREEN_MARGIN - height);

    guiGraphics.pose().pushPose();
    guiGraphics.pose().translate(0.0F, 0.0F, 400.0F);

    guiGraphics.fill(x, y, x + width, y + height, BACKGROUND_COLOR);
    guiGraphics.renderOutline(x, y, width, height, BORDER_COLOR);

    int leftX = x + PADDING;
    int rightX = leftX + leftWidth + COLUMN_GAP;
    int top = y + PADDING;
    int dividerX = rightX - (COLUMN_GAP + 1) / 2;
    guiGraphics.fill(dividerX, top, dividerX + 1, top + contentHeight, DIVIDER_COLOR);

    for (int i = 0; i < LEFT_COLUMN.size(); i++) {
      renderRow(guiGraphics, font, LEFT_COLUMN.get(i), leftX, top + i * ROW_HEIGHT, codeWidth);
    }
    for (int i = 0; i < RIGHT_COLORS.size(); i++) {
      renderRow(guiGraphics, font, RIGHT_COLORS.get(i), rightX, top + i * ROW_HEIGHT, codeWidth);
    }
    int separatorY = top + RIGHT_COLORS.size() * ROW_HEIGHT + (SEPARATOR_HEIGHT - 1) / 2 - 1;
    guiGraphics.fill(rightX, separatorY, rightX + rightWidth, separatorY + 1, DIVIDER_COLOR);
    int stylesTop = top + RIGHT_COLORS.size() * ROW_HEIGHT + SEPARATOR_HEIGHT;
    for (int i = 0; i < RIGHT_STYLES.size(); i++) {
      renderRow(guiGraphics, font, RIGHT_STYLES.get(i), rightX, stylesTop + i * ROW_HEIGHT, codeWidth);
    }

    guiGraphics.pose().popPose();
  }

  private static void renderRow(GuiGraphics guiGraphics, Font font, ChatFormatting formatting, int x, int y, int codeWidth) {
    Integer color = formatting.getColor();
    guiGraphics.fill(x, y, x + SWATCH_SIZE, y + SWATCH_SIZE, color != null ? 0xFF000000 | color : STYLE_SWATCH_COLOR);
    guiGraphics.renderOutline(x, y, SWATCH_SIZE, SWATCH_SIZE, SWATCH_BORDER_COLOR);

    int codeX = x + SWATCH_SIZE + GAP;
    guiGraphics.drawString(font, code(formatting), codeX, y, CODE_COLOR);
    guiGraphics.drawString(font, label(formatting), codeX + codeWidth + GAP, y,
        formatting == ChatFormatting.OBFUSCATED ? MUTED_LABEL_COLOR : LABEL_COLOR);
  }

  private static int columnWidth(Font font, int codeWidth, List<ChatFormatting> column) {
    int labelWidth = 0;
    for (ChatFormatting formatting : column) {
      labelWidth = Math.max(labelWidth, font.width(label(formatting)));
    }
    return SWATCH_SIZE + GAP + codeWidth + GAP + labelWidth;
  }

  private static FormattedCharSequence code(ChatFormatting formatting) {
    return FormattedCharSequence.forward("§" + formatting.getChar(), Style.EMPTY);
  }

  private static Component label(ChatFormatting formatting) {
    MutableComponent label = Component.translatable("unlocked_typing.formatting." + formatting.getName());
    if (formatting != ChatFormatting.BLACK && formatting != ChatFormatting.OBFUSCATED && formatting != ChatFormatting.RESET) {
      label.withStyle(formatting);
    }
    return label;
  }

  private static int clamp(int value, int min, int max) {
    return Math.max(min, Math.min(value, max));
  }
}
