package io.github.jason13official.unlocked_typing.impl.common;

import com.electronwill.nightconfig.core.Config;
import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.github.jason13official.unlocked_typing.Constants;
import io.github.jason13official.unlocked_typing.platform.Services;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.function.Consumer;

public class UnlockedTypingConfig {

  private static final Client CLIENT = new Client();

  public static Client client() {
    
    return CLIENT;
  }

  public static void load(Path configDir) {

    File configDirectory = new File(configDir.toUri());
    if (!configDirectory.isDirectory() && !configDirectory.mkdirs()) {
      Constants.LOG.info("Failed to get or create config directory {}", configDirectory.getAbsolutePath());
      return;
    }

    if (Services.PLATFORM.isClientSide()) {
      loadClientConfiguration(configDir, Constants.MOD_ID + "-client.toml");
    }
  }

  private static void loadClientConfiguration(Path configDir, String filename) {

    Path configFilepath = configDir.resolve(filename);
    File configFile = new File(configFilepath.toUri());

    // preserver order for our config
    boolean insertionOrderPreserved = Config.isInsertionOrderPreserved();
    Config.setInsertionOrderPreserved(true);
    try (CommentedFileConfig config = CommentedFileConfig.builder(configFile).build()) {

      // loading
      if (Files.exists(configFilepath)) {
        config.load();
      }
      CLIENT.setDisplayExamples(config.getOrElse("display_formatting_examples", true));

      // saving
      config.setComment("display_formatting_examples", " Whether examples should be displayed on relevant screens.");
      config.set("display_formatting_examples", CLIENT.shouldDisplayExamples());
      config.save();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      Constants.LOG.info("Failed to read or write config file {}", configFile.getAbsolutePath());
    }
    Config.setInsertionOrderPreserved(insertionOrderPreserved);
  }

  public static void overwriteClient() {
    overwrite(Constants.MOD_ID + "-client.toml", config -> {
      config.setComment("display_formatting_examples", " Whether examples should be displayed on relevant screens.");
      config.set("display_formatting_examples", CLIENT.shouldDisplayExamples());
    });
  }

  private static void overwrite(String filename, Consumer<CommentedFileConfig> writer) {
    Path configDir = Services.PLATFORM.getConfigDirectory();
    Path configFilepath = configDir.resolve(filename);
    File configFile = new File(configFilepath.toUri());

    try (CommentedFileConfig config = CommentedFileConfig.builder(configFile).build()) {

      if (configFile.exists()) {
        Files.copy(configFile.toPath(), configDir.resolve(filename + ".OLD"), StandardCopyOption.REPLACE_EXISTING);
        if (!configFile.delete()) {
          Constants.LOG.info("Failed to delete config file after copying as .OLD");
          return;
        }
      }

      writer.accept(config);
      config.save();

    } catch (Exception e) {
      System.out.println(e.getMessage());
      Constants.LOG.info("Failed to overwrite config file {}", configFile.getAbsolutePath());
    }
  }

  public static class Client {

    boolean displayExamples = true;

    public void setDisplayExamples(boolean displayExamples) {
      this.displayExamples = displayExamples;
    }

    public boolean shouldDisplayExamples() {
      return displayExamples;
    }
  }
}
