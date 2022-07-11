package votes.kp;

import org.openqa.selenium.WebDriver;
import service.configurations.Member;
import service.configurations.MemberConfig;
import service.webdriver.Browser;
import service.webdriver.model.Process;
import votes.VoteImpl;

import java.util.List;

public class VoteKP extends VoteImpl {

    public VoteKP(List<Browser> browsers, int count, List<Member> members) {
        super(browsers, count, members);
    }

    public VoteKP(List<Browser> browsers, int count, MemberConfig memberConfig) {
        super(browsers, count, memberConfig);
    }

    @Override
    public void vote(WebDriver driver, Process process) {
        getIpAddress(driver);

        pageManager = new PageManagerKP(driver, process, members);

        String voteUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";
        pageManager.votePage(voteUrl);
        pageManager.voteInput();
        pageManager.voteButton();
        pageManager.voteLogging(IPAddress);
    }
}
