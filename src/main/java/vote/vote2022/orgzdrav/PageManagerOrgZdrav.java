package vote.vote2022.orgzdrav;

import org.openqa.selenium.By;
import vote.browsers.Browsers;
import vote.pagemanager.PageManagerImpl;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.xpath;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(Browsers browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    @Override
    protected By getButtonLocator() {
        //return cssSelector("body > section.ftco-section > div > div > div.col-lg-4.column-sidebar > div.practice-vote.d-none.d-md-block > button");
        return xpath("/html/body/section[@class='ftco-section']//button[@type='button']");

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
