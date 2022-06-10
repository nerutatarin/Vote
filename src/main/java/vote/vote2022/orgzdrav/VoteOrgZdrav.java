package vote.vote2022.orgzdrav;

import org.openqa.selenium.WebDriver;
import vote.VoteImpl;
import vote.browsers.Browsers;
import vote.browsers.model.Process;

import java.util.List;

public class VoteOrgZdrav extends VoteImpl {
    private final String voteUrl = "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";

    public VoteOrgZdrav(List<Browsers> browsers, int count) {
        super(browsers, count);
    }

    public VoteOrgZdrav(Browsers browser, int count) {
        super(browser, count);
    }

    @Override
    public void vote(WebDriver driver, Process process) {
        getIpAddressJson(driver, process);

        pageManager = new PageManagerOrgZdrav(driver, process);
        pageManager.votePage(voteUrl);
        pageManager.voteButton();
        pageManager.voteLogging();
    }
}
