package example;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import utils.configurations.config.BrowserProperties;
import utils.configurations.config.BrowserType;

import java.io.InputStream;
import java.util.Map;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public void yamlParser() {

        InputStream is = getClass().getClassLoader().getResourceAsStream("browser_properties.yaml");
        Yaml yaml = new Yaml(new Constructor(BrowserProperties.class));

        BrowserProperties browserProperties = yaml.load(is);
        Map<String, BrowserType> browserType = browserProperties.getBrowsersType();
        BrowserType firefox = browserType.get("opera");
        String driverName = firefox.getName();

        System.out.println();
    }

    public static void main(String[] args) {

        BrowserProperties browserProperties = new BrowserProperties().yamlParser();
        Map<String, BrowserType> browsersType = browserProperties.getBrowsersType();
        BrowserType opera = browsersType.get("opera");
        String name = opera.getName();

    }
}
