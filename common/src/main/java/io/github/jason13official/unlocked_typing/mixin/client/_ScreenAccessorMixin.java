package io.github.jason13official.unlocked_typing.mixin.client;

import io.github.jason13official.unlocked_typing.api.client.accessor.ScreenAccessor;
import java.util.List;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.Renderable;
import net.minecraft.client.gui.components.events.GuiEventListener;
import net.minecraft.client.gui.narration.NarratableEntry;
import net.minecraft.client.gui.screens.Screen;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Screen.class)
public abstract class _ScreenAccessorMixin implements ScreenAccessor {

  @Shadow
  public int width;

  @Shadow
  protected Font font;

  @Shadow
  @Final
  private List<GuiEventListener> children;

  @Shadow
  @Final
  private List<NarratableEntry> narratables;

  @Shadow
  @Final
  private List<Renderable> renderables;

  @Override
  public int unlocked_typing$getWidth() {

    return this.width;
  }

  @Override
  public Font unlocked_typing$getFont() {

    return this.font;
  }

  @Override
  public <T extends GuiEventListener & Renderable & NarratableEntry> T unlocked_typing$addRenderableWidget(T widget) {

    this.renderables.add(widget);
    this.children.add(widget);
    this.narratables.add(widget);
    return widget;
  }
}
