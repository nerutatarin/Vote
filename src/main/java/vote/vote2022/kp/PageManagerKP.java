package vote.vote2022.kp;

import org.openqa.selenium.By;
import vote.PageManager;
import vote.browsers.Browsers;

import static utils.Thesaurus.SUBMIT_VOTE;

public class PageManagerKP extends PageManager {

    public PageManagerKP(Browsers browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    protected By getLocator() {
        return By.id(SUBMIT_VOTE);
    }
}
