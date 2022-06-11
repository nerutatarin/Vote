package vote.browsers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ProcessKiller;
import utils.configurations.config.BrowserProperties;
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

    private Capabilities capabilities() {
        return getOptions();
    }

    protected abstract <T> T getOptions();

    @Override
    public WebDriver getWebDriver() {
        killAllRunningProcesses();

        log.info(getBrowserName() + " Инициализация драйвера...");
        WebDriverManager().setup();

        webDriver = getBrowsersFactory();
        return webDriver;
    }

    private void killAllRunningProcesses() {
        ProcessKiller processKiller = new ProcessKiller();

        DRIVERS_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(getBrowserName()))
                .forEach(entry -> processKiller.killer(asList(entry.getKey(), entry.getValue())));
    }

    private void killProcess(String driverName, String browserName) {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(driverName);
        processKiller.killer(browserName);
    }

    private WebDriverManager WebDriverManager() {
        return WebDriverManager.getInstance(getBrowserName());
    }

    public String getBrowserName() {
        return toLowerCase(this.getClass().getSimpleName());
    }

    private BrowserProperties getBrowserProperties() {
        return new BrowserProperties().yamlParser();
    }

    private String getDriverName() {
        return getBrowserProperties().getBrowsersType().get(getBrowserName()).getName();
    }

    private WebDriver getBrowsersFactory() {
        switch (getBrowserName()) {
            case ("chrome"):
                return new ChromeDriver(capabilities());
            case ("chromium"):
                return new ChromeDriver(capabilities());
            case ("firefox"):
                return new FirefoxDriver(capabilities());
            case ("msedge"):
                return new EdgeDriver(capabilities());
            case ("opera"):
                return new OperaDriver(capabilities());
            default:
                return new FirefoxDriver(capabilities());
        }
    }

    public Process getProcess() {
        process.setBrowserName(getBrowserName());
        process.setProcessName(getCapabilities().getBrowserName());
        process.setProcessId(getProcessId());
        process.setDriverName(getDriverName());
        return process;
    }

    protected Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    protected abstract String getProcessId();

    protected Proxy getProxy() {
        Proxy proxy = new Proxy();
        if (isProxy) {
            Boolean alive = getWebProxy().getAlive();
            String proxyType = getWebProxy().getType();
            String host = getWebProxy().getHost();
            int port = getWebProxy().getPort();
            process.setHost(host);
            process.setPort(port);

            log.info(getBrowserName() + " Включен PROXY " + host + ":" + port + " " + proxyType + " alive: " + alive);
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

    public void webDriverClose() {
        String driverName = getDriverName();
        String browserName = getBrowserName();
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
}
