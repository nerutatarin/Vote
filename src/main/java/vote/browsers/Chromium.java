package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.chromium.ChromiumDriver;
import org.openqa.selenium.chromium.ChromiumOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import vote.driver.Driver;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static org.openqa.selenium.chrome.ChromeDriverLogLevel.*;
import static org.openqa.selenium.remote.CapabilityType.PAGE_LOAD_STRATEGY;
import static org.openqa.selenium.remote.CapabilityType.PROXY;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_KEY;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_VALUE;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class Chromium extends BrowsersImpl {
    private static final Logger log = getLogger(Chrome.class);

    @Override
    protected void webDriverInitialize() {
        //Driver driver = new Driver(CHROME_DRIVER_KEY, CHROME_DRIVER_VALUE);
        //driver.setPropertyDependsOnOS();
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
        //options.addArguments("--headless");
        /*options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--enable-precise-memory-info");*/
        //options.setCapability("--host-resolver-rules", "MAP * ~NOTFOUND, EXCLUDE " + PROXY_IP_ADDRESS + ":" + PROXY_PORT);

        /*options.setBrowserVersion("100.0.4896.75");
        options.setPlatformName("chrome");*/

        options.setExperimentalOption("useAutomationExtension", false);

        Duration timeout = ofSeconds(30);
        options.setPageLoadTimeout(timeout);
        options.setImplicitWaitTimeout(timeout);
        options.setScriptTimeout(timeout);

        options.setLogLevel(SEVERE);
        //options.setHeadless(true);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setProxy(getProxy());
        return options;
    }
}
