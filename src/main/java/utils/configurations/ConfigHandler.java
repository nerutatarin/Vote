/*
package utils.configurations;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.DumperOptions.FlowStyle;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class ConfigHandler {

    public static final Path configWebDriverPath = Paths.get("./browser_properties.yaml");

    private static ConfigHandler configHandler;

    ConfigWebDriver configWebDriver;

    public static ConfigHandler getInstance() throws FileNotFoundException {
        return getInstance(configWebDriverPath);
    }

    public static ConfigHandler getInstance(Path configPath) throws FileNotFoundException {
        if(configHandler == null) {
            configHandler = new ConfigHandler(configPath);
        }
        return configHandler;
    }

    private ConfigHandler(Path configWebDriverPath) throws FileNotFoundException {
        this.configWebDriver = loadConfig(configWebDriverPath);
    }

    public ConfigWebDriver loadConfig(Path configPath) throws FileNotFoundException {
        Constructor constructor = new Constructor(ConfigWebDriver.class);
        Yaml yaml = new Yaml(constructor);
        return yaml.load(new FileInputStream(configPath.toFile()));
    }

    public void dumpConfig() throws IllegalArgumentException, IllegalAccessException, IOException {
        dumpConfig(this.configWebDriver, this.configWebDriverPath);
    }

    public void dumpConfig(ConfigWebDriver config, Path configPath) throws IllegalArgumentException, IllegalAccessException, IOException {
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yml = new Yaml(options);
        yml.dump(config, new FileWriter(configPath.toFile()));
    }

    public ConfigWebDriver getConfigWebDriver() {
        return this.configWebDriver;
    }

    public BrowserType getBrowserType(String browserType) {
        return this.configWebDriver.getBrowserType().get(browserType);
    }
}*/
