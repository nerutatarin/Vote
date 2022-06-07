package vote;

import org.apache.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import utils.WriteToLog;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.Browsers;
import vote.browsers.model.Process;
import vote.pagemanager.PageManager;

import java.util.ArrayList;
import java.util.List;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = Logger.getLogger(VoteImpl.class);
    protected int count;
    //protected String ipAddrUrl = "https://api.ipify.org/";
    protected String ipAddrUrl = "https://ipinfo.io/?token=c2e2408c951023";
    protected PageManager pageManager;
    protected List<Browsers> browsersList = new ArrayList<>();
    protected Browsers browser;
    protected MyIpAddress myIpAddress;
    protected Process process;
    protected WebDriver webDriver;

    @Override
    public void run() {
        log.info("Начало работы...");
        for (int i = 0; i < count; i++) {
            try {
                init();
            } catch (TimeoutException e) {
                String processName = process.getProcessName();
                new WriteToLog(processName).error(e.getMessage());
                return;
            } catch (Exception e) {
                String processName = process.getProcessName();
                new WriteToLog(processName).error(e.getMessage());
            } finally {
                pageManager.voteClose();
            }
        }
    }

    public void init() {
        if (browsersList.isEmpty()) {
            vote(browser);
        } else {
            browsersList.forEach(this::vote);
        }
    }
}
