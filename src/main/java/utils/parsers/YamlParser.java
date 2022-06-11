package utils.parsers;

import org.yaml.snakeyaml.Yaml;
import utils.configurations.browsers.BrowserType;

import java.io.InputStream;
import java.util.Map;

public class YamlParser {

    private final String configName = "browser_properties.yaml";

    public Map<String, BrowserType> yamlParser(String configName) {
        if (configName == null || configName.isEmpty()) return null;

        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream(configName);

        return yaml.load(inputStream);
    }
}
