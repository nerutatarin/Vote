package vote.vote2022.orgzdrav;

import vote.browsers.BrowsersImpl;
import vote.pagemanager.PageManagerImpl;

import java.util.List;

import static java.util.Collections.emptyList;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(BrowsersImpl browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    @Override
    protected String getButtonLocator() {
        return "body > section.ftco-section > div > div > div.col-lg-4.column-sidebar > div.practice-vote > button";
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
