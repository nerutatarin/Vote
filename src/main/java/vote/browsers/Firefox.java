package vote.browsers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.*;
import utils.RandomUserAgent;

import java.time.Duration;

import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static java.lang.Boolean.FALSE;
import static java.lang.Boolean.TRUE;
import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static utils.Thesaurus.Capabilities.MOZ_PROCESS_ID;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_VALUE;

public class Firefox extends BrowsersImpl {

    @Override
    protected void webDriverInitialize() {
        /*Driver driver = new Driver(GECKO_DRIVER_KEY, GECKO_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();*/
        firefoxdriver().setup();
        //firefoxdriver().browserInDocker().create();
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
        options.setProfile(new FirefoxProfile());

        String userAgent = RandomUserAgent.getRandomUserAgent();
        options.addArguments("user-agent=" + userAgent);

        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("useAutomationExtension", false);
        //options.addArguments("-private");
        /*options.addPreference("browser.private.browsing.autostart", true);
        options.addPreference("network.proxy.socks_remote_dns", true);
        options.addPreference("toolkit.startup.max_resumed_crashes", "-1");
        options.addPreference("privacy.clearOnShutdown.cookies", true);
        options.addPreference("privacy.sanitize.sanitizeOnShutdown", true);
        options.addPreference("webgl.can-lose-context-in-foreground", false);
        options.addPreference("webgl.lose-context-on-memory-pressure", false);
        options.addPreference("media.peerconnection.enabled", false);
        options.addPreference("webgl.max-contexts", 1500);*/

        Duration timeout = ofSeconds(30);
        options.setPageLoadTimeout(timeout);
        options.setImplicitWaitTimeout(timeout);
        options.setScriptTimeout(timeout);

        options.setHeadless(true);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        //options.setProxy(getProxy());
        return options;
    }
}
