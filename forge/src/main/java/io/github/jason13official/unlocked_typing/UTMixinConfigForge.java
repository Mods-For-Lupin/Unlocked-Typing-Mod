package io.github.jason13official.unlocked_typing;

import io.github.jason13official.unlocked_typing.platform.Services;
import java.util.List;
import java.util.Set;
import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;

public class UTMixinConfigForge implements IMixinConfigPlugin {

  @Override
  public void onLoad(String mixinPackage) {

  }

  @Override
  public String getRefMapperConfig() {
    return "";
  }

  @Override
  public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {

    System.out.println("checking target and mixin " + targetClassName + " " + mixinClassName);

    if (mixinClassName.toLowerCase().contains("Production".toLowerCase())) {

      return !Services.PLATFORM.isDevelopmentEnvironment();
    }

    if (mixinClassName.toLowerCase().contains("Development".toLowerCase())) {

      return Services.PLATFORM.isDevelopmentEnvironment();
    }

    return true;
  }

  @Override
  public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) {

  }

  @Override
  public List<String> getMixins() {
    return List.of();
  }

  @Override
  public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

  }

  @Override
  public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) {

  }
}
