package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import vote.vote2022.driver.Driver;

import static java.lang.System.out;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Thesaurus.Drivers.*;

public class EdgeBrowser extends Browsers{
    @Override
    protected void setDriverProperty() {
        out.println("Init Edge Driver...");
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
        out.println("Edge options...");
        EdgeOptions edgeOptions = new EdgeOptions();
        //edgeOptions.addArguments("--incognito");
        edgeOptions.addArguments("--disable-gpu");
        edgeOptions.addArguments("--start-maximized");
        edgeOptions.addArguments("--ignore-ssl-errors");
        edgeOptions.addArguments("--disable-extensions");
        edgeOptions.addArguments("--dns-prefetch-disable");
        edgeOptions.addArguments("--disable-popup-blocking");
        edgeOptions.addArguments("--ignore-certificate-errors");
        edgeOptions.addArguments("--enable-precise-memory-info");

        edgeOptions.setAcceptInsecureCerts(true);
        //edgeOptions.setHeadless(false);
        edgeOptions.setPageLoadStrategy(NORMAL);
        edgeOptions.setCapability("proxy", getProxy());
        return edgeOptions;
    }
}
