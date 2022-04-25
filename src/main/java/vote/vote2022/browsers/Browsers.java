package vote.vote2022.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import vote.vote2022.browsers.model.BrowserProcess;

import static java.util.concurrent.TimeUnit.SECONDS;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

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
        webDriver.manage().deleteAllCookies();
        //webDriver.manage().timeouts().implicitlyWait(10, SECONDS);
        webDriver.manage().timeouts().pageLoadTimeout(120, SECONDS);
        webDriver.manage().timeouts().implicitlyWait(30, SECONDS);
        return webDriver;
    }

    public BrowserProcess getProcess() {
        BrowserProcess process = new BrowserProcess();
        process.setProcessName(getCapabilities().getBrowserName());
        process.setProcessId(getProcessId());
        process.setDriverName(getDriverName());
        //process.setDriverName(getCapabilities().getCapability(MOZ_GECKODRIVER_VERSION).toString(); //0.31.0
        return process;
    }

    protected Proxy getProxy() {
        Proxy proxy = new Proxy();
        proxy.setSocksProxy(PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        proxy.setSocksVersion(5);
        return proxy;
    }

    protected abstract String getProcessId();

    protected abstract String getDriverName();

    protected Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    protected abstract WebDriver getDriverInstance();
}
