package vote.browsers;

import org.openqa.selenium.opera.OperaOptions;
import utils.RandomUserAgent;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;

public class Opera extends BrowsersImpl {

    public Opera() {
    }

    public Opera(boolean isProxy) {
        super(isProxy);
    }

    public Opera(boolean isHeadless, boolean isProxy) {
        super(isHeadless, isProxy);
    }

    @Override
    protected String getProcessId() {
        return null;
    }

    @Override
    protected OperaOptions getOptions() {
        OperaOptions options = new OperaOptions();
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

        if (isHeadless) options.addArguments("--headless");

        Duration timeout = ofSeconds(30);
        options.setPageLoadTimeout(timeout);
        options.setImplicitWaitTimeout(timeout);
        options.setScriptTimeout(timeout);

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setProxy(getProxy());
        return options;
    }
}
