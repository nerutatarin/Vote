package votes;

import example.VoteException;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import service.configurations.Member;
import service.configurations.MemberConfig;
import service.exception.BrowserNotFoundException;
import service.pagemanager.PageManager;
import service.retrofit.api.myip.IpSeeipService;
import service.webdriver.Browser;
import service.webdriver.model.Process;
import utils.WriteToLog;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;

import java.util.List;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = Logger.getLogger(VoteImpl.class);

    protected int count;
    protected PageManager pageManager;
    protected List<Browser> browsers;
    protected Browser browser;
    protected IPAddress IPAddress;
    protected String browserName;
    protected List<Member> members;
    protected MemberConfig memberConfig;

    public VoteImpl(List<Browser> browsers, int count, List<Member> members) {
        this.browsers = browsers;
        this.count = count;
        this.members = members;
    }

    public VoteImpl(List<Browser> browsers, int count, MemberConfig memberConfig) {
        this.browsers = browsers;
        this.count = count;
        this.memberConfig = memberConfig;
    }

    public void vote(Boolean isThread) {
        if (isThread) {
            start();
        } else {
            init();
        }
    }

    @Override
    public void run() {
        init();
    }

    public void init() {
        for (int i = 0; i < count; i++) {
            try {
                vote(i);
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

    private void vote(int i) {
        if (browsers.isEmpty()) throw new BrowserNotFoundException();

        for (Browser browser : browsers) {
            this.browser = browser;
            browserName = browser.getBrowserName();
            log.info(browserName + " Попытка № " + i);
            vote(getWebDriver(browser), getProcess(browser));
        }
    }

    private Process getProcess(Browser browser) {
        return browser.getProcess();
    }

    private WebDriver getWebDriver(Browser browser) {
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
