package service.pagemanager;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.browsers.model.Process;
import service.configurations.Participants;
import service.pagemanager.model.ResultsCount;
import service.pagemanager.model.VotePage;
import utils.Utils;
import utils.WriteToLog;
import utils.ipaddress.model.IPAddress;
import votes.kp.PageManagerKP;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;

public abstract class PageManagerImpl implements PageManager {
    private static final Logger log = getLogger(PageManagerKP.class);

    protected WebDriverWait wait;
    protected WebDriver webDriver;
    protected Process process;
    protected String browserName;
    protected List<VotePage> votePageList;
    protected Participants participants;

    public PageManagerImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public PageManagerImpl(WebDriver webDriver, Process process) {
        this.webDriver = webDriver;
        this.process = process;
        browserName = process.getBrowserName();
        participants = getParticipants();
    }

    private Participants getParticipants() {
        return new Participants().parse();
    }

    public void votePage(String baseUrl) {
        int timeout = 30;
        wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeout));
        log.info(browserName + " Запуск страницы голосования " + baseUrl);
        webDriver.get(baseUrl);
        wait.until(ExpectedConditions.titleIs("Клиника года - 2022. Уфа."));

        String fileName = "src/resources/cookie_after_vote.json";
        saveCookie(fileName);

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
            log.info(browserName + " Ищем " + inp + " ...");
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(id(inp)));
            webElement.click();
            log.info(browserName + " Проставлен " + inp);
        });
    }

    protected abstract List<String> getInputsListLocatorById();

    public void voteButton() {
        log.info(browserName + " Ищем кнопку голосования: ");
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(getButtonLocator()));
        webElement.click();
        log.info(browserName + " Кнопка голосования нажата: ");

        sleep();

        String fileName = "src/resources/cookie_before_vote.json";
        saveCookie(fileName);
    }

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Во время паузы произошла ошибка: " + e.getMessage());
        }
    }

    protected abstract By getButtonLocator();

    public void voteLogging(IPAddress IPAddress) {
        Document pageSource = getPageSource();
        if (pageSource == null) return;

        List<ResultsCount> resultsCounts = getVoteCountList(pageSource);
        if (resultsCounts == null || resultsCounts.isEmpty()) return;

        for (ResultsCount vCount : resultsCounts) {
            if (getInputsListLocatorById().contains(vCount.getInputId())) {
                log.info(browserName + " " + vCount);

                String ip = IPAddress.getIp();
                String country = IPAddress.getCountry();
                String count = vCount.getCount().trim();

                String title = vCount.getTitle();
                WriteToLog writeToLog = new WriteToLog(browserName, title);
                writeToLog.ipCountryCount(ip, country, count);
            }
        }
    }

    private void saveCookie(String fileName) {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        if (cookies.size() == 0) {
            log.info(browserName + " " + "Не удалось получить куки!");
            return;
        }

        Utils.objectToFileWithGson(cookies, fileName);
    }

    private Document getPageSource() {
        return Jsoup.parse(webDriver.getPageSource());
    }

    protected abstract List<ResultsCount> getVoteCountList(Document pageSource);
}
