package utils.configurations.config;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.LinkedHashMap;
import java.util.Map;

public class BrowserProperties {

    private Map<String, BrowserType> browsersType = new LinkedHashMap<>();

    public Map<String, BrowserType> getBrowsersType() {
        return browsersType;
    }

    public void setBrowsersType(Map<String, BrowserType> browsersType) {
        this.browsersType = browsersType;
    }

    public BrowserProperties yamlParser() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("browser_properties.yaml");
        Yaml yaml = new Yaml(new Constructor(BrowserProperties.class));
        return yaml.load(is);
    }
}
