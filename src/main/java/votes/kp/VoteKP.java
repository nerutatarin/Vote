package votes.kp;

import org.openqa.selenium.WebDriver;
import service.browsers.Browsers;
import service.browsers.model.Process;
import votes.VoteImpl;

import java.util.List;

public class VoteKP extends VoteImpl {

    public VoteKP() {
    }

    public VoteKP(Browsers browser) {
        super(browser);
    }

    public VoteKP(List<Browsers> browsers, int count) {
        super(browsers, count);
    }

    public VoteKP(Browsers browser, int count) {
        super(browser, count);
    }

    @Override
    public void vote(WebDriver driver, Process process) {
        getIpAddress(driver);

        pageManager = new PageManagerKP(driver, process);

        String voteUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";
        pageManager.votePage(voteUrl);
        pageManager.voteInput();
        pageManager.voteButton();
        pageManager.voteLogging(IPAddress);
    }
}
