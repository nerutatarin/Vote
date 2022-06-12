package vote.vote2022.orgzdrav;

import org.openqa.selenium.WebDriver;
import vote.VoteImpl;
import vote.browsers.Browsers;
import vote.browsers.model.Process;

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
