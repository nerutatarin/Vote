package example;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import utils.configurations.browsers.BrowserProperties;
import utils.configurations.browsers.BrowserType;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.Browsers;
import vote.browsers.Firefox;

import java.io.InputStream;
import java.util.Map;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public WebDriver getWebDriver() {
        Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

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
        Config config = new Config();
        WebDriver webDriver = config.getWebDriver();

        IPAddressGetter ipAddressGetter = new IPAddressGetterByJson(webDriver);
        MyIpAddress ipAddress = ipAddressGetter.getIpAddress();
        System.out.println(ipAddress);
    }
}
