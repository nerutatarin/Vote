package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;

import static org.openqa.selenium.remote.CapabilityType.*;
import static utils.Thesaurus.Drivers.OPERA_DRIVER_VALUE;

public class Opera extends BrowsersImpl {
    private static final Logger log = Logger.getLogger(Opera.class);

    @Override
    protected void setDriverProperty() {
        log.info("Init Opera Driver...");
        /*Driver driver = new Driver(OPERA_DRIVER_KEY, OPERA_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        WebDriverManager.operadriver().setup();
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
        log.info("Opera options...");
        OperaOptions options = new OperaOptions();
        options.addArguments("--enable-automation");
        //options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--enable-precise-memory-info");

        options.setAcceptInsecureCerts(true);
        options.setCapability(PAGE_LOAD_STRATEGY, "eager");
        options.setCapability(SUPPORTS_JAVASCRIPT, true);
        options.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        options.setCapability(HAS_NATIVE_EVENTS, true);
        options.setCapability(PROXY, getProxy());
        return options;
    }
}
