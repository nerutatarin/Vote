package vote.vote2022.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.opera.OperaOptions;
import vote.vote2022.driver.Driver;

import static java.lang.System.out;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Thesaurus.Drivers.OPERA_DRIVER_KEY;
import static utils.Thesaurus.Drivers.OPERA_DRIVER_VALUE;

public class OperaBrowser extends Browsers {
    @Override
    protected void setDriverProperty() {
        out.println("Init Opera Driver...");
        Driver driver = new Driver(OPERA_DRIVER_KEY, OPERA_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
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

    private Capabilities getOptions() {
        out.println("Opera options...");
        OperaOptions operaOptions = new OperaOptions();
        //operaOptions.addArguments("--proxy-server=socks5://" + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        //operaOptions.addArguments("enable-automation");
        //operaOptions.addArguments("--headless");
        operaOptions.addArguments("--incognito");
        operaOptions.addArguments("--disable-gpu");
        operaOptions.addArguments("--start-maximized");
        operaOptions.addArguments("--ignore-ssl-errors");
        operaOptions.addArguments("--disable-extensions");
        operaOptions.addArguments("--dns-prefetch-disable");
        operaOptions.addArguments("--disable-popup-blocking");
        operaOptions.addArguments("--ignore-certificate-errors");
        operaOptions.addArguments("--enable-precise-memory-info");

        //operaOptions.setAcceptInsecureCerts(true);
        operaOptions.setPageLoadStrategy(NORMAL);
        operaOptions.setCapability("proxy", getProxy());
        return operaOptions;
    }
}
