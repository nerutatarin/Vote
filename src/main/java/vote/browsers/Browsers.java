package vote.browsers;

import org.openqa.selenium.WebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class Browsers implements BrowsersImpl {

    @Override
    public WebDriver init() {
        initGecko();
        return settingBrowser();
    }

    private void initGecko() {
        setDriverProperty();
    }

    protected abstract void setDriverProperty();


    private WebDriver settingBrowser() {
        WebDriver webDriver = getDriverInstance();
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
        return webDriver;
    }

    protected abstract WebDriver getDriverInstance();
}
