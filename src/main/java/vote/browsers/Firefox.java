package vote.browsers;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.RandomUserAgent;

import java.time.Duration;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.PageLoadStrategy.EAGER;
import static org.openqa.selenium.firefox.FirefoxDriverLogLevel.FATAL;
import static utils.Thesaurus.Capabilities.MOZ_PROCESS_ID;

public class Firefox extends BrowsersImpl {

    public Firefox() {
    }

    public Firefox(boolean isProxy) {
        super(isProxy);
    }

    public Firefox(boolean isHeadless, boolean isProxy) {
        super(isHeadless, isProxy);
    }

    @Override
    protected String getProcessId() {
        return getCapabilities().getCapability(MOZ_PROCESS_ID).toString();
    }

    @Override
    protected FirefoxOptions getOptions() {
        FirefoxOptions options = new FirefoxOptions();
        options.setProfile(new FirefoxProfile());

        options.addArguments("-private");

        String userAgent = RandomUserAgent.getRandomUserAgent();
        options.addPreference("general.useragent.override", userAgent);

        //options.addArguments("user-agent=" + userAgent);
        options.addPreference("dom.webdriver.enabled", false);
        options.addPreference("dom.webnotifications.enabled", false);
        options.addPreference("useAutomationExtension", false);
        options.addPreference("toolkit.startup.max_resumed_crashes", "-1");

        Duration timeout = ofSeconds(30);
        options.setPageLoadTimeout(timeout);
        options.setImplicitWaitTimeout(timeout);
        options.setScriptTimeout(timeout);

        options.setLogLevel(FATAL);
        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setHeadless(isHeadless);
        options.setProxy(getProxy());
        return options;
    }
}
