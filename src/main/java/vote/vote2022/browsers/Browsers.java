package vote.vote2022.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import static java.util.concurrent.TimeUnit.SECONDS;

public abstract class Browsers implements BrowsersImpl {
    public WebDriver webDriver;

    @Override
    public WebDriver getWebDriver() {
        setDriverProperty();
        return settingBrowser();
    }

    protected abstract void setDriverProperty();

    private WebDriver settingBrowser() {
        webDriver = getDriverInstance();
        webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
        return webDriver;
    }

/*    public Capabilities getCapabilities() {
        return ((HasCapabilities) webDriver).getCapabilities();
    }*/

    protected Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    protected abstract WebDriver getDriverInstance();
}
