package service.browsers;

import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.chrome.ChromeDriverLogLevel;
import org.openqa.selenium.chrome.ChromeOptions;
import service.configurations.Options;

import java.time.Duration;

public class Chrome extends BrowsersFactory {

    @Override
    protected ChromeOptions getOptions() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--enable-automation");
        //options.addArguments("--headless");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-update");
        options.addArguments("--incognito");
        options.addArguments("--disable-gpu");
        options.addArguments("--ignore-ssl-errors");
        options.addArguments("--disable-extensions");
        options.addArguments("--dns-prefetch-disable");
        options.addArguments("--disable-popup-blocking");
        options.addArguments("--enable-precise-memory-info");

        mainOptions(options);
        return options;
    }

    private void mainOptions(ChromeOptions options) {
        Options browserOptions = getBrowserOptions();

        options.setPageLoadTimeout(Duration.ofSeconds(browserOptions.getPageLoadTimeout()));
        options.setImplicitWaitTimeout(Duration.ofSeconds(browserOptions.getImplicitWaitTimeout()));
        options.setScriptTimeout(Duration.ofSeconds(browserOptions.getScriptTimeout()));

        options.setAcceptInsecureCerts(browserOptions.isAcceptInsecureCerts());
        options.setLogLevel(ChromeDriverLogLevel.valueOf(browserOptions.getLogLevel()));
        options.setPageLoadStrategy(PageLoadStrategy.valueOf(browserOptions.getPageLoadStrategy()));

        options.setHeadless(browserOptions.isHeadless());
        options.setProxy(getProxy());
    }
}
