package example;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import utils.configurations.config.BrowserProperties;
import utils.configurations.config.ConfigWebDriver;
import utils.configurations.config.Customer;

import java.io.InputStream;
import java.util.Map;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public Map<String, ConfigWebDriver> yamlParser() {
        Yaml yaml = new Yaml();

        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("browser_properties.yaml");

        return yaml.load(inputStream);
    }

    public void testParser() {
        Yaml yaml = new Yaml(new Constructor(Customer.class));
        InputStream inputStream = this.getClass()
                .getClassLoader()
                .getResourceAsStream("test_config.yaml");
        Customer customer = yaml.load(inputStream);

        int age = customer.getAge();
        System.out.println(age);
    }

    public static void main(String[] args) {
       /* ConfigWebDriver browserProperties = new ConfigWebDriver();
        Map<String, BrowserProperties> browserType = browserProperties.getBrowserType();
        log.info(browserType);
        String firefox = browserType.get("Firefox").getKey();*/

        Config config = new Config();
        Map<String, ConfigWebDriver> parser = config.yamlParser();
        System.out.println(parser);

        ConfigWebDriver configWebDriver = parser.get("firefox");
        BrowserProperties browserType = configWebDriver.getBrowserType();
        System.out.println(browserType);

        String driverName = browserType.getDriverName();
        System.out.println(driverName);

 /*       Config config = new Config();
        config.testParser();*/
    }
}
