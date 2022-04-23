package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static java.lang.System.out;
import static java.lang.System.setProperty;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Thesaurus.Drivers.WEBDRIVER_CHROME_DRIVER;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class ChromeBrowser extends Browsers {

    @Override
    protected void setDriverProperty() {
        out.println("Init Chrome drivers...");
        setProperty(WEBDRIVER_CHROME_DRIVER, "src/resources/chromedriver_100.0.4896.60");
    }


    @Override
    protected WebDriver getDriverInstance() {
        return new ChromeDriver(getOptions());
    }

    protected ChromeOptions getOptions() {
        out.println("Chrome options...");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--proxy-server=socks5://" + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        chromeOptions.addArguments("enable-automation");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("window-size=1024,768");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setHeadless(true);
        chromeOptions.setPageLoadStrategy(NORMAL);
        return chromeOptions;
    }
}
