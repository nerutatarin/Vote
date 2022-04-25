package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import vote.vote2022.driver.Driver;

import static java.lang.System.out;
import static utils.Thesaurus.Capabilities.MOZ_PROCESS_ID;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_KEY;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_VALUE;

public class FirefoxBrowser extends Browsers {

    @Override
    protected void setDriverProperty() {
        out.println("Init Firefox Driver...");
        Driver driver = new Driver(GECKO_DRIVER_KEY, GECKO_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
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
        out.println("Firefox options...");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        //firefoxOptions.addPreference("network.proxy.type", 1);
        //firefoxOptions.addPreference("network.proxy.socks", PROXY_IP_ADDRESS);
        //firefoxOptions.addPreference("network.proxy.socks_port", PROXY_PORT);
        firefoxOptions.addPreference("network.proxy.socks_remote_dns", true);
        firefoxOptions.addPreference("toolkit.startup.max_resumed_crashes", "-1");
        firefoxOptions.addPreference("privacy.clearOnShutdown.cookies", true);
        firefoxOptions.addPreference("privacy.sanitize.sanitizeOnShutdown", true);
        firefoxOptions.addPreference("webgl.can-lose-context-in-foreground", false);
        firefoxOptions.addPreference("webgl.lose-context-on-memory-pressure", false);
        firefoxOptions.addPreference("media.peerconnection.enabled", false);
        firefoxOptions.addPreference("webgl.max-contexts", 1500);

        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setHeadless(true);
        firefoxOptions.setCapability("proxy", getProxy());
        return firefoxOptions;
    }


}
