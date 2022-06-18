package votes.orgzdrav;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.VotingPage;
import service.webdriver.model.Process;

import static org.openqa.selenium.By.xpath;

public class PageManagerOrgZdrav extends PageManagerImpl {

    public PageManagerOrgZdrav(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    @Override
    protected VotingPage getPageBeforeVoting(Document pageSource) {
        return null;
    }

    @NotNull
    @Override
    protected String getPageTitle() {
        return "Оргздрав";
    }

    @Override
    protected void allowInputs() {

    }

    @Override
    protected By getButtonLocator() {
        return xpath("/html/body/section[@class='ftco-section']//button[@type='button']");
    }

    @Override
    protected VotingPage getPageAfterVoting(Document pageSource) {
        return null;
    }
}
