package io.github.jason13official.unlocked_typing.platform;

import io.github.jason13official.unlocked_typing.platform.services.IPlatformHelper;
import java.nio.file.Path;
import net.minecraft.world.item.SpawnEggItem;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.loading.FMLLoader;

public class ForgePlatformHelper implements IPlatformHelper {

  @Override
  public String getPlatformName() {

    return "Forge";
  }

  @Override
  public boolean isModLoaded(String modId) {

    return ModList.get().isLoaded(modId);
  }

  @Override
  public boolean isDevelopmentEnvironment() {

    return !FMLLoader.isProduction();
  }

  @Override
  public boolean isClientSide() {
    return FMLLoader.getDist() == Dist.CLIENT;
  }

  @Override
  public Path getGameDirectory() {

    return FMLLoader.getGamePath();
  }

  @Override
  public SpawnEggItem egg() {

    // crashes
    return new ForgeSpawnEggItem(null, 0, 0, null);

    // doesn't crash
    // return null
  }
}