package votes.orgzdrav;

import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.browsers.model.Process;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.PageVote;
import service.pagemanager.model.ResultsVote;

import java.util.List;

import static java.util.Collections.emptyList;
import static org.openqa.selenium.By.xpath;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    @Override
    protected By getButtonLocator() {
        return xpath("/html/body/section[@class='ftco-section']//button[@type='button']");
    }

    @Override
    protected void getVotePages(Document pageSource, List<PageVote> pageVotes) {

    }

    @Override
    protected List<String> getInputsListLocatorById() {
        return emptyList();
    }

    @Override
    protected List<ResultsVote> getVoteCountList(Document pageSource) {
        return null;
    }

}
