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
                init();
                log.info(" Попытка № " + count);
            } catch (TimeoutException e) {
                new WriteToLog(browserName).error(e.getMessage());
                return;
            } catch (Exception e) {
                new WriteToLog(browserName).error(e.getMessage());
            } finally {
                pageManager.voteClose();
            }
        }
    }

    public void init() {
        if (!browsersList.isEmpty()) {
            for (Browsers driver : browsersList) {
                browserName = driver.getInstanceName();
                vote(getWebDriver(driver), getProcess(driver));
            }
        } else {
            browserName = browser.getInstanceName();
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
        //String ipAddrUrl = "https://api.ipify.org/";
        String ipAddrUrl = "https://ipinfo.io/?token=c2e2408c951023";
        myIpAddress = IPAddressGetter.getIpAddressJson(webDriver, process, ipAddrUrl);
        return myIpAddress;
    }
}
