package service.webdriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import service.configurations.Browser;
import service.configurations.BrowsersConfig;
import service.configurations.Options;
import service.configurations.ProxySettings;
import service.exception.BrowserNotFoundException;
import service.proxy.ProxiesFactory;
import service.webdriver.model.Process;
import utils.ProcessKiller;

import java.util.Map;

import static jdk.nashorn.internal.objects.NativeString.toLowerCase;
import static org.apache.log4j.Logger.getLogger;

public abstract class BrowserImpl implements service.webdriver.Browser {
    private static final Logger log = getLogger(BrowserImpl.class);

    protected final BrowsersConfig browsersConfig;
    protected final String browserName;
    protected boolean noProxy;
    protected WebDriver webDriver;
    protected Process process;

    public BrowserImpl() {
        this.browsersConfig = getBrowserConfig();
        this.noProxy = getProxySettings().getNoProxy();
        this.process = new Process();
        this.browserName = getBrowserName();
        killAllRunningProcesses();
    }

    private BrowsersConfig getBrowserConfig() {
        return new BrowsersConfig().parse();
    }

    protected ProxySettings getProxySettings() {
        return browsersConfig.getProxySettings();
    }

    public String getBrowserName() {
        return toLowerCase(this.getClass().getSimpleName());
    }

    protected Map<String, Browser> getBrowsers() {
        return browsersConfig.getBrowserMap();
    }

    protected Browser getBrowser() {
        return getBrowsers().get(getBrowserName());
    }

    protected Options getBrowserOptions() {
        return getBrowser().getOptions();
    }

    @Override
    public WebDriver getWebDriver() {
        log.info(browserName + " Инициализация драйвера...");

        // TODO: 04.07.2022 динамический выбор драйвера
        WebDriverManager().setup();
        //System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/unix/chromedriver");

        try {
            webDriver = getBrowsersFactory(browserName);
        } catch (Exception e) {
            if (webDriver == null) log.error(browserName + " не удалось иницилизировать драйвер");
            throw new BrowserNotFoundException();
        }

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
        return getBrowsers().get(browserName).getDriver().getName();
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