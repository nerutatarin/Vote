package vote.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static utils.Thesaurus.Capabilities.MOZ_PROCESS_ID;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_VALUE;

public class Firefox extends BrowsersImpl {

    @Override
    protected void setDriverProperty() {
        /*Driver driver = new Driver(GECKO_DRIVER_KEY, GECKO_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        firefoxdriver().setup();
    }

    @Override
    protected String getProcessId() {
        return getCapabilities().getCapability(MOZ_PROCESS_ID).toString();
    }

    @Override
    protected String getDriverName() {
        return GECKO_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new FirefoxDriver(getOptions());
    }

    private FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("-private");
        options.addPreference("network.proxy.socks_remote_dns", true);
        options.addPreference("toolkit.startup.max_resumed_crashes", "-1");

        options.setHeadless(true);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setProxy(getProxy());
        return options;
    }
}
