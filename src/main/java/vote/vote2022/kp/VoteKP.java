package vote.vote2022.kp;

import org.openqa.selenium.WebDriver;
import vote.VoteImpl;
import vote.browsers.Browsers;
import vote.browsers.model.Process;

import java.util.List;

public class VoteKP extends VoteImpl {
    private final String voteUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";

    public VoteKP(List<Browsers> browsers, int count) {
        super(browsers, count);
    }

    public VoteKP(Browsers browser, int count) {
        super(browser, count);
    }

    @Override
    public void vote(WebDriver driver, Process process) {
        getIpAddressJson(driver, process);

        pageManager = new PageManagerKP(driver, process, myIpAddress);
        pageManager.votePage(voteUrl);
        pageManager.voteInput();
        pageManager.voteButton();
        pageManager.voteLogging();
    }
}
