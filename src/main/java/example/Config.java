package example;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.configurations.browsers.BrowserProperties;
import utils.configurations.browsers.BrowserType;
import utils.configurations.browsers.Options;
import utils.configurations.browsers.ProxySettings;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;
import utils.retrofit.services.myip.IpSeeipService;
import utils.retrofit.services.myip.response.IPAddressInfo;
import vote.browsers.Browsers;
import vote.browsers.Firefox;

import java.util.Map;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public static void main(String[] args) {
        yamlParserTest();
    }

    public static WebDriver getWebDriverNoProxy() {
        Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

    public static WebDriver getWebDriverProxy() {
        Browsers browser = new Firefox(true);
        return browser.getWebDriver();
    }

    private static void yamlParserTest() {
        BrowserProperties browserProperties = new BrowserProperties().parse();
        Map<String, BrowserType> browsersType = browserProperties.getBrowsersType();
        BrowserType firefox = browsersType.get("firefox");
        System.out.println(firefox.getName());

        Options options = firefox.getOptions();
        System.out.println(options);

        boolean isHeadless = options.isHeadless();
        System.out.println(isHeadless);

        ProxySettings proxySettings = browserProperties.getProxySettings();
        System.out.println(proxySettings);
    }

    private static void ipAddressGetterTest(WebDriver webDriver) {
        IPAddressGetter ipAddressGetter = new IPAddressGetterByJson(webDriver);
        IPAddress ipAddress = ipAddressGetter.getIpAddress();
        System.out.println(ipAddress);
    }

    private static void ipAddressApiService() {
        IpSeeipService ipSeeipService = new IpSeeipService();

        IPAddress ipJson = ipSeeipService.getIpJson();
        String ip = ipJson.getIp();
        System.out.println(ip);

        IPAddressInfo ipGeoip = ipSeeipService.getIpGeoip();
        String ipGeoipIp = ipGeoip.getIp();
        System.out.println(ipGeoipIp);
    }
}
