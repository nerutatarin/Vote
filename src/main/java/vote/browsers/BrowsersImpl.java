package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ProcessKiller;
import utils.retrofit.services.webproxy.freeproxy.FreeProxyService;
import utils.retrofit.services.webproxy.freeproxy.response.FreeProxyMedium;
import vote.browsers.model.Process;

import static java.util.Arrays.asList;
import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.Drivers.DRIVERS_MAP;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public abstract class BrowsersImpl implements Browsers {
    private static final Logger log = getLogger(BrowsersImpl.class);
    protected boolean isHeadless;
    protected boolean isProxy;
    protected WebDriver webDriver;
    protected Process process;

    public BrowsersImpl() {
        this(true, false);
    }

    public BrowsersImpl(boolean isProxy) {
        this(true, isProxy);
    }

    public BrowsersImpl(boolean isHeadless, boolean isProxy) {
        this.isHeadless = isHeadless;
        this.isProxy = isProxy;
        process = new Process();
    }

    @Override
    public WebDriver getWebDriver() {
        killAllRunningProcesses();

        log.info(getInstanceName() + " Инициализация драйвера...");
        WebDriverManager.getInstance().setup();
        webDriverInitialize();

        webDriver = getDriverInstance();
        return webDriver;
    }

    protected abstract void webDriverInitialize();

    protected abstract WebDriver getDriverInstance();

    public Process getProcess() {
        process.setBrowserName(getInstanceName());
        process.setProcessName(getCapabilities().getBrowserName());
        process.setProcessId(getProcessId());
        process.setDriverName(getDriverName());
        return process;
    }

    public String getInstanceName() {
        return toLowerCase(this.getClass().getSimpleName());
    }

    protected Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    protected abstract String getProcessId();

    protected abstract String getDriverName();

    protected Proxy getProxy() {
        Proxy proxy = new Proxy();
        if (isProxy) {
            Boolean alive = getWebProxy().getAlive();
            String proxyType = getWebProxy().getType();
            String host = getWebProxy().getHost();
            int port = getWebProxy().getPort();
            process.setHost(host);
            process.setPort(port);

            log.info(getInstanceName() + " Включен PROXY " + host + ":" + port + " " + proxyType + " alive: " + alive);
            if (proxyType.equals("Socks4")) {
                proxy.setSocksProxy(host + ":" + port);
                proxy.setSocksVersion(4);
            }
            if (proxyType.equals("Socks5")) {
                proxy.setSocksProxy(host + ":" + port);
                proxy.setSocksVersion(5);
            }
            if (proxyType.contains("Http")) {
                proxy.setHttpProxy(host + ":" + port);
            }
        } else {
            proxy.setNoProxy("");
        }
        return proxy;
    }

    private void killAllRunningProcesses() {
        ProcessKiller processKiller = new ProcessKiller();

        DRIVERS_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(getInstanceName()))
                .forEach(entry -> processKiller.killer(asList(entry.getKey(), entry.getValue())));
    }

    private Proxy getTorProxy() {
        Proxy proxy = new Proxy();
        proxy.setSocksProxy(PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        proxy.setSocksVersion(5);
        return proxy;
    }

    private FreeProxyMedium getWebProxy() {
        FreeProxyService freeProxyService = new FreeProxyService();
        FreeProxyMedium proxyMedium = freeProxyService.getProxyMedium();
        return proxyMedium.getAlive() ? proxyMedium : getWebProxy();
    }

    public void voteClose() {
        String driverName = getDriverName();
        String browserName = getInstanceName();
        try {
            log.info(browserName + " Завершаем работу драйвера: " + driverName);
            webDriver.quit();
            log.info(browserName + " Работа драйвера " + driverName + " успешно завершена!");
        } catch (Exception e) {
            log.debug(browserName + " При попытке завершить работу драйвера " + driverName + " возникла непредвиденная ошибка: " + e);
            log.info(browserName + " Завершаем процесс драйвера: " + driverName);
            killProcess(driverName, browserName);
        }
    }

    private void killProcess(String driverName, String browserName) {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(driverName);
        processKiller.killer(browserName);
    }
}
