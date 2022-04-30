package vote.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import vote.browsers.model.Process;

import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public abstract class BrowsersImpl implements Browsers {
    public WebDriver webDriver;

    @Override
    public WebDriver getWebDriver() {
        setDriverProperty();
        return settingBrowser();
    }

    protected abstract void setDriverProperty();

    private WebDriver settingBrowser() {
        webDriver = getDriverInstance();
        //webDriver.manage().deleteAllCookies();
        return webDriver;
    }

    public Process getProcess() {
        Process process = new Process();
        process.setProcessName(getCapabilities().getBrowserName());
        process.setProcessId(getProcessId());
        process.setDriverName(getDriverName());
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
