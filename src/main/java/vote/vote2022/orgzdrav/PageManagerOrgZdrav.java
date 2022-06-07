package vote.vote2022.orgzdrav;

import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import vote.browsers.model.Process;
import vote.pagemanager.PageManagerImpl;
import vote.pagemanager.model.VoteCount;
import vote.pagemanager.model.VotePage;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.xpath;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(WebDriver webDriver, Process process) {
        this.webDriver = webDriver;
        this.process = process;
        getBrowserName();
    }

    @Override
    protected By getButtonLocator() {
        return xpath("/html/body/section[@class='ftco-section']//button[@type='button']");
    }

    @Override
    protected void getVotePages(Document pageSource, List<VotePage> votePages) {

    }

    @Override
    protected List<String> getInputsListLocatorById() {
        return emptyList();
    }

    @Override
    protected List<VoteCount> getVoteCountList(Document pageSource) {
        return null;
    }

}
