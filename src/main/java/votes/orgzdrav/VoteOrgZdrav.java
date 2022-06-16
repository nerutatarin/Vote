package votes.orgzdrav;

import org.openqa.selenium.WebDriver;
import service.webdriver.Browsers;
import service.webdriver.model.Process;
import votes.VoteImpl;

import java.util.List;

public class VoteOrgZdrav extends VoteImpl {

    public VoteOrgZdrav() {
    }

    public VoteOrgZdrav(Browsers browser) {
        super(browser);
    }

    public VoteOrgZdrav(List<Browsers> browsers, int count) {
        super(browsers, count);
    }

    public VoteOrgZdrav(Browsers browser, int count) {
        super(browser, count);
    }

    @Override
    public void vote(WebDriver driver, Process process) {
        getIpAddress(driver);

        pageManager = new PageManagerOrgZdrav(driver, process);

        String voteUrl = "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";
        pageManager.votePage(voteUrl);
        pageManager.voteButton();
        pageManager.voteLogging(IPAddress);
    }
}
