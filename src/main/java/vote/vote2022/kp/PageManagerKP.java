package vote.vote2022.kp;

import org.openqa.selenium.By;
import vote.pagemanager.PageManagerImpl;
import vote.browsers.BrowsersImpl;

import static utils.Thesaurus.SUBMIT_VOTE;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(BrowsersImpl browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    protected By getLocator() {
        return By.id(SUBMIT_VOTE);
    }
}
