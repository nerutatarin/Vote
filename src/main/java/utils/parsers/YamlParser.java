package utils.parsers;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

public class YamlParser {

    public static <T> T yamlParser(Class<T> clazz, String configName) {
        if (configName == null || configName.isEmpty()) return null;

        InputStream is = YamlParser.class.getClassLoader().getResourceAsStream(configName);
        Yaml yaml = new Yaml(new Constructor(clazz));
        return yaml.load(is);
    }
}
