package utils.yaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;

import static utils.Thesaurus.DirectoriesName.CONFIG_PATH;

public class YamlParser {

    public static <T> T yamlParser(Class<T> clazz, String configName) {
        if (configName == null || configName.isEmpty()) return null;

        InputStream is = YamlParser.class.getClassLoader().getResourceAsStream(CONFIG_PATH + configName);
        Yaml yaml = new Yaml(new Constructor(clazz));
        return yaml.load(is);
    }
}
