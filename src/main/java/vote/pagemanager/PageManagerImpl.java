package vote.pagemanager;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.WriteToLog;
import utils.configurations.Participants;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.model.Process;
import vote.pagemanager.model.VoteCount;
import vote.pagemanager.model.VotePage;
import vote.vote2022.kp.PageManagerKP;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;

public abstract class PageManagerImpl implements PageManager {
    private static final Logger log = getLogger(PageManagerKP.class);

    protected WebDriverWait wait;
    protected WebDriver webDriver;
    protected Process process;
    protected String processName;
    protected List<VotePage> votePageList;
    protected Participants participants;

    public PageManagerImpl(WebDriver webDriver, Process process) {
        this.webDriver = webDriver;
        this.process = process;
        processName = process.getProcessName();
        participants = getParticipants();
    }

    private Participants getParticipants() {
        return new Participants().yamlParser();
    }

    public void votePage(String baseUrl) {
        int timeout = 30;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
        log.info(processName + " Запуск страницы голосования " + baseUrl);
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.titleIs("Клиника года - 2022. Уфа."));

        Document pageSource = getPageSource();
        if (pageSource == null) throw new TimeoutException();
        votePageList = parseVotePage(pageSource);
    }

    public List<VotePage> parseVotePage(Document pageSource) {
        List<VotePage> votePages = new ArrayList<>();
        getVotePages(pageSource, votePages);
        return votePages;
    }

    protected abstract void getVotePages(Document pageSource, List<VotePage> votePages);

    public void voteInput() {
        getInputsListLocatorById().forEach(inp -> {
            log.info(processName + " Ищем " + inp + " ...");
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(id(inp)));
            webElement.click();
            log.info(processName + " Проставлен " + inp);
        });
    }

    protected abstract List<String> getInputsListLocatorById();

    public void voteButton() {
        log.info(processName + " Ищем кнопку голосования: ");
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(getButtonLocator()));
        webElement.click();
        log.info(processName + " Кнопка голосования нажата: ");

        sleep();
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Во время паузы произошла ошибка: " + e.getMessage());
        }
    }

    protected abstract By getButtonLocator();

    public void voteLogging(MyIpAddress myIpAddress) {
        Document pageSource = getPageSource();
        if (pageSource == null) return;

        List<VoteCount> voteCounts = getVoteCountList(pageSource);
        if (voteCounts == null || voteCounts.isEmpty()) return;

        for (VoteCount vCount : voteCounts) {
            if (getInputsListLocatorById().contains(vCount.getInputId())) {
                log.info(processName + " " + vCount);

                String ip = myIpAddress.getIp();
                String country = myIpAddress.getCountry();
                String count = vCount.getCount().trim();

                String title = vCount.getTitle();
                WriteToLog writeToLog = new WriteToLog(processName, title);
                writeToLog.ipCountryCount(ip, country, count);
            }
        }
    }

    private Document getPageSource() {
        return Jsoup.parse(webDriver.getPageSource());
    }

    protected abstract List<VoteCount> getVoteCountList(Document pageSource);
}
