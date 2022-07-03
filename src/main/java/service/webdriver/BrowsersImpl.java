package service.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import service.configurations.BrowserConfig;
import service.configurations.Browsers;
import service.configurations.Options;
import service.configurations.ProxySettings;
import service.proxy.ProxiesFactory;
import service.webdriver.model.Process;
import utils.ProcessKiller;

import java.util.Map;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static org.apache.log4j.Logger.getLogger;

public abstract class BrowsersImpl implements service.webdriver.Browsers {
    private static final Logger log = getLogger(BrowsersImpl.class);

    protected final BrowserConfig browserConfig;
    protected final String browserName;
    protected boolean noProxy;
    protected WebDriver webDriver;
    protected Process process;

    public BrowsersImpl() {
        this.browserConfig = getBrowserConfig();
        this.noProxy = getProxySettings().getNoProxy();
        this.process = new Process();
        this.browserName = getBrowserName();
        killAllRunningProcesses();
    }

    private BrowserConfig getBrowserConfig() {
        return new BrowserConfig().parse();
    }

    protected ProxySettings getProxySettings() {
        return browserConfig.getProxySettings();
    }

    public String getBrowserName() {
        return toLowerCase(this.getClass().getSimpleName());
    }

    protected Map<String, Browsers> getBrowsers() {
        return browserConfig.getBrowsers();
    }

    protected Browsers getBrowser() {
        return getBrowsers().get(getBrowserName());
    }

    protected Options getBrowserOptions() {
        return getBrowser().getOptions();
    }

    @Override
    public WebDriver getWebDriver() {
        log.info(browserName + " Инициализация драйвера...");
        //WebDriverManager().setup();
        System.setProperty("webdriver.chrome.driver", "/home/zaripov/IdeaProjects/Vote/src/resources/unix/chromedriver");

        webDriver = getBrowsersFactory(browserName);
        return webDriver;
    }

    private WebDriverManager WebDriverManager() {
        return WebDriverManager.getInstance(browserName);
    }

    protected abstract WebDriver getBrowsersFactory(String browserName);

    public Process getProcess() {
        process.setBrowserName(browserName);
        process.setDriverName(getDriverName());
        process.setNoProxy(noProxy);
        return process;
    }

    private String getDriverName() {
        return getBrowsers().get(browserName).getName();
    }

    protected abstract <T> T getOptions();

    public Capabilities getCapabilities() {
        return ((RemoteWebDriver) webDriver).getCapabilities();
    }

    protected Proxy getNoProxy() {
        ProxySettings proxySettings = getProxySettings();
        service.proxy.Proxy proxy = ProxiesFactory.getInstance(proxySettings);
        return proxy.getProxy(browserName);
    }

    public void webDriverClose() {
        String driverName = getDriverName();
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

    private void killAllRunningProcesses() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killAllRunningProcesses(browserName);
    }
}