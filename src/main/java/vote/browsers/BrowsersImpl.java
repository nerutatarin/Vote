package vote.browsers;

import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ProcessKiller;
import vote.browsers.model.Process;

import static java.util.Arrays.asList;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.Drivers.DRIVERS_MAP;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public abstract class BrowsersImpl implements Browsers {
    private static final Logger log = getLogger(BrowsersImpl.class);

    public WebDriver webDriver;

    @Override
    public WebDriver getWebDriver() {
        killAllRunningProcesses();

        log.info("Инициализация " + getInstanceName() + " драйвера...");
        webDriverInitialize();
        return settingBrowser();
    }

    protected abstract void webDriverInitialize();

    private WebDriver settingBrowser() {
        webDriver = getDriverInstance();
        return webDriver;
    }

    public Process getProcess() {
        Process process = new Process();
        process.setBrowserName(getInstanceName());
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

    private String getInstanceName() {
        return toLowerCase(this.getClass().getSimpleName());
    }

    private void killAllRunningProcesses() {
        ProcessKiller processKiller = new ProcessKiller();

        DRIVERS_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(getInstanceName()))
                .forEach(entry -> processKiller.killer(asList(entry.getKey(), entry.getValue())));
    }
}
