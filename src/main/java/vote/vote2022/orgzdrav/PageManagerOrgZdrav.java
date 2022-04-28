package vote.vote2022.orgzdrav;

import org.openqa.selenium.By;
import vote.pagemanager.PageManagerImpl;
import vote.browsers.BrowsersImpl;

import static org.openqa.selenium.By.cssSelector;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(BrowsersImpl browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    @Override
    protected By getLocator() {
        return cssSelector("body > section.ftco-section > div > div > div.col-lg-4.column-sidebar > div.practice-vote > button");
        //return By.xpath("/html/body/section[2]/div/div/div[2]/div[3]/button");
        //return By.className("btn");
    }
}
