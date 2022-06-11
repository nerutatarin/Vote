package vote;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.WriteToLog;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.Browsers;
import vote.browsers.model.Process;
import vote.pagemanager.PageManager;

import java.util.ArrayList;
import java.util.List;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = Logger.getLogger(VoteImpl.class);

    protected int count;
    protected PageManager pageManager;
    protected List<Browsers> browsersList = new ArrayList<>();
    protected Browsers browser;
    protected MyIpAddress myIpAddress;
    protected String browserName;

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
            } catch (TimeoutException e) {
                log.error(browserName + " Превышено время ожидания загрузки страницы!");
                new WriteToLog(browserName).error(e.getLocalizedMessage());
                return;
            } catch (Exception e) {
                log.error(browserName + " Неизвестная ошибка!");
                new WriteToLog(browserName).error(e.getMessage());
            } finally {
                browser.webDriverClose();
            }
        }
    }

    public void init() {
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

    public Process getProcess(Browsers browser) {
        return browser.getProcess();
    }

    public WebDriver getWebDriver(Browsers browser) {
        return browser.getWebDriver();
    }

    @Nullable
    public MyIpAddress getIpAddressJson(WebDriver webDriver, Process process) {
        String ipAddrUrl = "https://ipinfo.io/?token=c2e2408c951023";
        myIpAddress = IPAddressGetter.getIpAddressJson(webDriver, process, ipAddrUrl);
        return myIpAddress;
    }
}
