package votes;

import example.VoteException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import service.pagemanager.PageManager;
import service.retrofit.api.myip.IpSeeipService;
import service.webdriver.Browsers;
import service.webdriver.browsers.Firefox;
import service.webdriver.model.Process;
import utils.WriteToLog;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;

import java.util.ArrayList;
import java.util.List;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = Logger.getLogger(VoteImpl.class);

    protected int count;
    protected PageManager pageManager;
    protected List<Browsers> browsersList = new ArrayList<>();
    protected Browsers browser;
    protected IPAddress IPAddress;
    protected String browserName;

    public VoteImpl() {
        this(new Firefox(), 1);
    }

    public VoteImpl(Browsers browser) {
        this(browser, 1);
    }

    public VoteImpl(List<Browsers> browsers, int count) {
        this.browsersList = browsers;
        this.count = count;
    }

    public VoteImpl(Browsers browser, int count) {
        this.browser = browser;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                log.info(browser.getBrowserName() + " Попытка № " + i);
                init();
            } catch (VoteException e) {
                new WriteToLog(browserName).error(e.getMessage());
            } catch (TimeoutException e) {
                log.error(browserName + " Превышено время ожидания загрузки страницы!");
                new WriteToLog(browserName).error(e.getLocalizedMessage());
            } catch (Exception e) {
                log.error(browserName + " Неизвестная ошибка!");
                new WriteToLog(browserName).error(e.getMessage());
            } finally {
                browser.webDriverClose();
            }
        }
    }

    private void init() {
        if (!browsersList.isEmpty()) {
            for (Browsers driver : browsersList) {
                browserName = driver.getBrowserName();
                vote(getWebDriver(driver), getProcess(driver));
            }
        } else {
            browserName = browser.getBrowserName();
            vote(getWebDriver(browser), getProcess(browser));
        }
    }

    private Process getProcess(Browsers browser) {
        return browser.getProcess();
    }

    private WebDriver getWebDriver(Browsers browser) {
        return browser.getWebDriver();
    }

    @Nullable
    protected IPAddress getIpAddress(WebDriver webDriver) {
        if (browser.getProcess().getNoProxy()) {
            IPAddressGetter ipAddressGetter = new IPAddressGetterByJson(webDriver);
            IPAddress = ipAddressGetter.getIpAddress();
        }
        IpSeeipService ipSeeipService = new IpSeeipService();
        IPAddress = ipSeeipService.getIpJson();
        return IPAddress;
    }
}
