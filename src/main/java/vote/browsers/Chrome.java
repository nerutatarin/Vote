package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static org.openqa.selenium.chrome.ChromeDriverLogLevel.ALL;
import static org.openqa.selenium.remote.CapabilityType.PAGE_LOAD_STRATEGY;
import static org.openqa.selenium.remote.CapabilityType.PROXY;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_VALUE;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class Chrome extends BrowsersImpl {

    @Override
    protected void setDriverProperty() {
        /*Driver driver = new Driver(CHROME_DRIVER_KEY, CHROME_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        WebDriverManager.chromedriver().setup();
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
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-automation");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--enable-precise-memory-info");
        options.setCapability("--host-resolver-rules", "MAP * ~NOTFOUND, EXCLUDE " + PROXY_IP_ADDRESS + ":" + PROXY_PORT);

        options.setLogLevel(ALL);
        options.setHeadless(true);
        options.setAcceptInsecureCerts(true);
        options.setCapability(PAGE_LOAD_STRATEGY, NORMAL);
        options.setCapability(PROXY, getProxy());
        return options;
    }
}
