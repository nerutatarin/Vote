package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import utils.RandomUserAgent;

import java.time.Duration;
import java.util.Collections;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_VALUE;

public class Chromium extends BrowsersImpl {

    public Chromium() {
    }

    public Chromium(boolean isProxy) {
        super(isProxy);
    }

    public Chromium(boolean isHeadless, boolean isProxy) {
        super(isHeadless, isProxy);
    }

    @Override
    protected void webDriverInitialize() {
        WebDriverManager.chromedriver().setup();
    }

    @Override
    protected String getProcessId() {
        return null;
    }

    @Override
    protected String getDriverName() {
        return CHROME_DRIVER_VALUE;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
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

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setHeadless(isHeadless);
        options.setProxy(getProxy());
        return options;
    }
}
