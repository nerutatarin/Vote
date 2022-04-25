package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vote.vote2022.driver.Driver;

import static java.lang.System.out;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_KEY;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_VALUE;

public class ChromeBrowser extends Browsers {

    @Override
    protected void setDriverProperty() {
        out.println("Init Chrome Drivers...");
        Driver driver = new Driver(CHROME_DRIVER_KEY, CHROME_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
    }

    @Override
    protected String getProcessId() {
        return null;
    }

    @Override
    protected String getDriverName() {
        return CHROME_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        out.println("Chrome options...");
        ChromeOptions chromeOptions = new ChromeOptions();
        //chromeOptions.addArguments("--proxy-server=socks5://" + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        //chromeOptions.addArguments("--proxy-server=socks5://127.0.0.1:9050");
        //chromeOptions.addArguments("enable-automation");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--incognito");
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--ignore-ssl-errors");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--disable-popup-blocking");
        chromeOptions.addArguments("--ignore-certificate-errors");
        chromeOptions.addArguments("--enable-precise-memory-info");

        chromeOptions.setAcceptInsecureCerts(true);
        //chromeOptions.setHeadless(false);
        chromeOptions.setPageLoadStrategy(NORMAL);
        chromeOptions.setCapability("proxy", getProxy());
        return chromeOptions;
    }
}
