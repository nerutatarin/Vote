package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.firefox.FirefoxDriverLogLevel.TRACE;
import static org.openqa.selenium.remote.CapabilityType.*;
import static utils.Thesaurus.Drivers.EDGE_DRIVER_VALUE;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class Edge extends BrowsersImpl {
    private static final Logger log = getLogger(Edge.class);

    @Override
    protected void setDriverProperty() {
        log.info("Init Edge Driver...");
        /*Driver driver = new Driver(EDGE_DRIVER_KEY, EDGE_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        WebDriverManager.edgedriver().setup();
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
        options.addArguments("--enable-automation");
        options.addArguments("--headless");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--enable-precise-memory-info");
        options.setCapability("--host-resolver-rules", "MAP * ~NOTFOUND, EXCLUDE " + PROXY_IP_ADDRESS + ":" + PROXY_PORT);

        options.setAcceptInsecureCerts(true);
        options.setCapability(PAGE_LOAD_STRATEGY, "eager");
        options.setCapability(PROXY, getProxy());
        return options;
    }
}
