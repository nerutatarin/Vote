package votes.orgzdrav;

import org.openqa.selenium.WebDriver;
import service.configurations.Member;
import service.configurations.MemberConfig;
import service.webdriver.Browser;
import service.webdriver.model.Process;
import votes.VoteImpl;

import java.util.List;

public class VoteOrgZdrav extends VoteImpl {

    public VoteOrgZdrav(List<Browser> browsers, int count, List<Member> members) {
        super(browsers, count, members);
    }

    public VoteOrgZdrav(List<Browser> browsers, int count, MemberConfig memberConfig) {
        super(browsers, count, memberConfig);
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
