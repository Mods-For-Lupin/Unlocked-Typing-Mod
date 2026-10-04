package io.github.jason13official.unlocked_typing.platform;

import io.github.jason13official.unlocked_typing.platform.services.IPlatformHelper;
import java.nio.file.Path;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;

public class FabricPlatformHelper implements IPlatformHelper {

  @Override
  public String getPlatformName() {

    return "Fabric";
  }

  @Override
  public boolean isModLoaded(String modId) {

    return FabricLoader.getInstance().isModLoaded(modId);
  }

  @Override
  public boolean isDevelopmentEnvironment() {

    return FabricLoader.getInstance().isDevelopmentEnvironment();
  }

  @Override
  public boolean isClientSide() {
    return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
  }

  @Override
  public Path getGameDirectory() {

    return FabricLoader.getInstance().getGameDir();
  }
}
