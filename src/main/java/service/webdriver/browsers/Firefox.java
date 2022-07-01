package service.webdriver.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import service.configurations.Options;
import service.webdriver.drivers.DriversFactory;
import utils.RandomUserAgent;

import java.time.Duration;

public class Firefox extends DriversFactory {

    @Override
    protected Capabilities getOptions() {
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

        mainOptions(options);

        return options;
    }

    private void mainOptions(FirefoxOptions options) {
        Options browserOptions = getBrowserOptions();

        options.setPageLoadTimeout(Duration.ofSeconds(browserOptions.getPageLoadTimeout()));
        options.setImplicitWaitTimeout(Duration.ofSeconds(browserOptions.getImplicitWaitTimeout()));
        options.setScriptTimeout(Duration.ofSeconds(browserOptions.getScriptTimeout()));

        options.setAcceptInsecureCerts(browserOptions.isAcceptInsecureCerts());
        options.setLogLevel(FirefoxDriverLogLevel.valueOf(browserOptions.getLogLevel()));
        options.setPageLoadStrategy(PageLoadStrategy.valueOf(browserOptions.getPageLoadStrategy()));

        options.setHeadless(browserOptions.isHeadless());
        options.setProxy(getProxy());
    }
}
