package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import vote.vote2022.driver.Driver;
import vote.vote2022.browsers.model.BrowserProcess;

import static java.lang.System.out;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_KEY;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_VALUE;
import static utils.Thesaurus.MozCapabilities.MOZ_PROCESS_ID;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class FirefoxBrowser extends Browsers {

    @Override
    protected void setDriverProperty() {
        out.println("Init Firefox Driver...");
        Driver driver = new Driver(GECKO_DRIVER_KEY, GECKO_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
    }

    public BrowserProcess getProcess(){
        BrowserProcess process = new BrowserProcess();
        process.setProcessId(getCapabilities().getCapability(MOZ_PROCESS_ID).toString());
        process.setProcessName(getCapabilities().getBrowserName());
        return process;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new FirefoxDriver(getOptions());
    }

    private FirefoxOptions getOptions() {
        out.println("Firefox options...");
        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.addPreference("network.proxy.type", 1);
        firefoxOptions.addPreference("network.proxy.socks", PROXY_IP_ADDRESS);
        firefoxOptions.addPreference("network.proxy.socks_port", PROXY_PORT);
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
        return firefoxOptions;
    }
}
