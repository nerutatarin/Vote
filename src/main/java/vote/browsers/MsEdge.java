package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import utils.RandomUserAgent;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static utils.Thesaurus.Drivers.MSEDGE_DRIVER_VALUE;

public class MsEdge extends BrowsersImpl {

    @Override
    protected void webDriverInitialize() {
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
        //options.addArguments("--enable-automation");
        options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));
        options.setExperimentalOption("useAutomationExtension", false);
        // Fixing 255 Error crashes
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        // Changing the user agent / browser fingerprint
        String userAgent = RandomUserAgent.getRandomUserAgent();
        options.addArguments("--user-agent=" + userAgent);
        options.addArguments("--window-size=1920,1080");
        // Options to trick bot detection
        options.addArguments("--disable-blink-features=AutomationControlled");
        // Other
        options.addArguments("--disable-infobars");
        options.addArguments("--incognito");

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
