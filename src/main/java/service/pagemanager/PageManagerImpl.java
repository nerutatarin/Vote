package service.pagemanager;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import service.configurations.MemberConfig;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;
import service.webdriver.model.Process;
import utils.Utils;
import utils.WriteToLog;
import utils.ipaddress.model.IPAddress;
import utils.yaml.YamlParser;
import votes.kp.PageManagerKP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import static java.time.Duration.ofSeconds;
import static org.apache.log4j.Logger.getLogger;
import static org.jsoup.Jsoup.parse;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static utils.Thesaurus.FilesNameJson.COOKIE_AFTER_VOTING_JSON;
import static utils.Thesaurus.FilesNameJson.COOKIE_BEFORE_VOTING_JSON;
import static utils.Thesaurus.FilesNameYaml.MEMBER_CONFIG_YAML;
import static utils.Utils.nullOrEmpty;
import static utils.jackson.JsonMapper.objectToFilePretty;

public abstract class PageManagerImpl implements PageManager {
    private static final Logger log = getLogger(PageManagerKP.class);

    protected WebDriverWait wait;
    protected WebDriver webDriver;
    protected Process process;
    protected String browserName;
    protected MemberConfig memberConfig;
    protected List<String> inputs = new ArrayList<>();

    public PageManagerImpl(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public PageManagerImpl(WebDriver webDriver, Process process) {
        this.webDriver = webDriver;
        this.process = process;
        browserName = process.getBrowserName();
        memberConfig = getMemberConfig();
    }

    private MemberConfig getMemberConfig() {
        return YamlParser.parse(MemberConfig.class, MEMBER_CONFIG_YAML);
    }

    public void votePage(String baseUrl) {
        log.info(browserName + " Запуск страницы голосования " + baseUrl);

        int timeout = 30;
        wait = new WebDriverWait(webDriver, ofSeconds(timeout));
        webDriver.get(baseUrl);

        saveCookie(COOKIE_AFTER_VOTING_JSON);
    }

    protected abstract VotingPage getPageBeforeVoting(Document pageSource);

    @NotNull
    protected abstract String getPageTitle();

    public void voteInput() {
        allowInputs();
        if (Utils.nullOrEmpty(inputs)) return;

        for (String inp : inputs) {
            log.info(browserName + " Ищем " + inp + " ...");
            WebElement webElement = wait.until(elementToBeClickable(id(inp)));
            webElement.click();
            log.info(browserName + " Проставлен " + inp);
        }
    }

    protected abstract void allowInputs();

    public void voteButton() {
        log.info(browserName + " Ищем кнопку голосования: ");
        WebElement webElement = wait.until(elementToBeClickable(getButtonLocator()));
        webElement.click();
        log.info(browserName + " Кнопка голосования нажата: ");

        sleep();

        saveCookie(COOKIE_BEFORE_VOTING_JSON);
    }

    protected abstract By getButtonLocator();

    private void sleep() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException("Во время паузы произошла ошибка: " + e.getMessage());
        }
    }

    private void saveCookie(String fileName) {
        Set<Cookie> cookies = webDriver.manage().getCookies();
        if (cookies.size() == 0) {
            log.info(browserName + " " + "Не удалось получить куки!");
            return;
        }

        objectToFilePretty(cookies, fileName);
    }

    public void voteLogging(IPAddress IPAddress) {
        Document pageSource = getPageSource();

        VotingPage votingPage = getPageAfterVoting(pageSource);
        List<Member> memberList = votingPage.getMemberList();

        if (nullOrEmpty(memberList)) return;

        for (Member member : memberList) {

            if (nullOrEmpty(inputs)) return;

            if (inputs.contains(member.getInput())) {
                log.info(browserName + " " + member);
                String ip = IPAddress.getIp();
                String country = IPAddress.getCountry();

                String title = member.getTitle();
                int count = member.getCount();

                WriteToLog writeToLog = new WriteToLog(browserName, title);
                writeToLog.ipCountryCount(ip, country, count);
            }
        }
    }

    protected abstract VotingPage getPageAfterVoting(Document pageSource);

    protected Document getPageSource() {
        Boolean isPageLoaded = wait.until(ExpectedConditions.titleIs(getPageTitle()));
        if (!isPageLoaded) throw new TimeoutException();

        return parse(webDriver.getPageSource());
    }
}
