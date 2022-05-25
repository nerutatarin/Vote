package vote.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.operadriver;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static org.openqa.selenium.chrome.ChromeDriverLogLevel.OFF;
import static utils.Thesaurus.Drivers.OPERA_DRIVER_VALUE;

public class Opera extends BrowsersImpl {

    @Override
    protected void webDriverInitialize() {
        /*Driver driver = new Driver(OPERA_DRIVER_KEY, OPERA_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        operadriver().setup();
    }

    @Override
    protected String getProcessId() {
        return null;
    }

    @Override
    protected String getDriverName() {
        return OPERA_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new OperaDriver(getOptions());
    }

    private OperaOptions getOptions() {
        OperaOptions options = new OperaOptions();

        options.addArguments("--headless");
        options.addArguments("--disable-update");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--enable-precise-memory-info");

        Duration timeout = ofSeconds(30);
        options.setPageLoadTimeout(timeout);
        options.setImplicitWaitTimeout(timeout);
        options.setScriptTimeout(timeout);

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setProxy(getProxy());
        return options;
    }
}
