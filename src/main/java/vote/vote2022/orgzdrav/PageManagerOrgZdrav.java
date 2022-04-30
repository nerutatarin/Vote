package vote.vote2022.orgzdrav;

import org.openqa.selenium.By;
import vote.browsers.Browsers;
import vote.pagemanager.PageManagerImpl;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.cssSelector;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(Browsers browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    @Override
    protected By getButtonLocator() {
        return cssSelector("body > section.ftco-section > div > div > div.col-lg-4.column-sidebar > div.practice-vote > button");
        //return By.xpath("/html/body/section[2]/div/div/div[2]/div[3]/button");
        //return By.className("btn");
    }

    @Override
    protected String getIpAddressLocator() {
        return "#ipcontent > table > tbody > tr:nth-child(2) > td";
    }

    @Override
    protected List<String> getInputsListLocatorById() {
        return emptyList();
    }
}
