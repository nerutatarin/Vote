package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static org.openqa.selenium.chrome.ChromeDriverLogLevel.ALL;
import static org.openqa.selenium.firefox.FirefoxDriverLogLevel.ERROR;
import static org.openqa.selenium.remote.CapabilityType.*;
import static utils.Thesaurus.Drivers.MSEDGE_DRIVER_VALUE;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class MsEdge extends BrowsersImpl {

    @Override
    protected void setDriverProperty() {
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
        return MSEDGE_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new EdgeDriver(getOptions());
    }

    private EdgeOptions getOptions() {
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--enable-automation");
        options.addArguments("--incognito");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--enable-precise-memory-info");

        options.setHeadless(false);
        options.setAcceptInsecureCerts(true);
        options.setCapability(PAGE_LOAD_STRATEGY, NORMAL);
        options.setCapability(PROXY, getProxy());
        return options;
    }
}
