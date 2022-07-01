package service.webdriver.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.edge.EdgeOptions;
import service.configurations.Options;
import service.webdriver.drivers.DriversFactory;
import utils.RandomUserAgent;

import java.time.Duration;
import java.util.Collections;

public class MsEdge extends DriversFactory {

    @Override
    protected Capabilities getOptions() {
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

        mainOptions(options);
        return options;
    }

    private void mainOptions(EdgeOptions options) {
        Options browserOptions = getBrowserOptions();

        options.setPageLoadTimeout(Duration.ofSeconds(browserOptions.getPageLoadTimeout()));
        options.setImplicitWaitTimeout(Duration.ofSeconds(browserOptions.getImplicitWaitTimeout()));
        options.setScriptTimeout(Duration.ofSeconds(browserOptions.getScriptTimeout()));

        options.setAcceptInsecureCerts(browserOptions.isAcceptInsecureCerts());
        options.setPageLoadStrategy(PageLoadStrategy.valueOf(browserOptions.getPageLoadStrategy()));

        options.setHeadless(browserOptions.isHeadless());
        options.setProxy(getProxy());
    }
}
