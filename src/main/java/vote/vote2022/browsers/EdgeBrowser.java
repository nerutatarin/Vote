package vote.vote2022.browsers;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import vote.vote2022.driver.Driver;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static org.openqa.selenium.remote.CapabilityType.*;
import static org.openqa.selenium.remote.CapabilityType.HAS_NATIVE_EVENTS;
import static utils.Thesaurus.Drivers.EDGE_DRIVER_KEY;
import static utils.Thesaurus.Drivers.EDGE_DRIVER_VALUE;

public class EdgeBrowser extends Browsers {
    private static final Logger log = getLogger(EdgeBrowser.class);

    @Override
    protected void setDriverProperty() {
        log.info("Init Edge Driver...");
        Driver driver = new Driver(EDGE_DRIVER_KEY, EDGE_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
    }

    @Override
    protected String getProcessId() {
        return null;
    }

    @Override
    protected String getDriverName() {
        return EDGE_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new EdgeDriver(getOptions());
    }

    private EdgeOptions getOptions() {
        log.info("Edge options...");
        EdgeOptions options = new EdgeOptions();
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
        options.setPageLoadStrategy(NORMAL);

        //options.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
        options.setCapability(SUPPORTS_JAVASCRIPT, true);
        options.setCapability(ELEMENT_SCROLL_BEHAVIOR, true);
        options.setCapability(HAS_NATIVE_EVENTS, true);
        options.setCapability(PROXY, getProxy());
        return options;
    }
}
