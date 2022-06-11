package vote.browsers;

import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.PageLoadStrategy.EAGER;

public class Chrome extends BrowsersImpl {

    public Chrome() {
    }

    public Chrome(boolean isProxy) {
        super(isProxy);
    }

    public Chrome(boolean isHeadless, boolean isProxy) {
        super(isHeadless, isProxy);
    }

    @Override
    protected String getProcessId() {
        return null;
    }

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

        options.setAcceptInsecureCerts(true);
        options.setPageLoadStrategy(EAGER);
        options.setHeadless(isHeadless);
        options.setProxy(getProxy());
        return options;
    }
}
