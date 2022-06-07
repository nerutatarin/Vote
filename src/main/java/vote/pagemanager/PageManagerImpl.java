package vote.pagemanager;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ProcessKiller;
import utils.WriteToLog;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.model.Process;
import vote.pagemanager.model.VoteCount;
import vote.pagemanager.model.VotePage;
import vote.vote2022.kp.PageManagerKP;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static org.apache.log4j.Logger.getLogger;
import static org.jsoup.Jsoup.parse;
import static org.openqa.selenium.By.id;

public abstract class PageManagerImpl implements PageManager {
    private static final Logger log = getLogger(PageManagerKP.class);

    protected WebDriverWait wait;
    protected WebDriver webDriver;
    protected Process process;
    protected String processName;
    protected MyIpAddress myIpAddress;
    protected List<VotePage> votePageList;

    protected void getBrowserName() {
        processName = process.getProcessName();
    }

    public void votePage(String baseUrl) throws TimeoutException {
        int timeout = 30;
        wait = new WebDriverWait(webDriver, ofSeconds(timeout));
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
        try {
            WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(getButtonLocator()));
            webElement.click();
            log.info(processName + " Кнопка голосования нажата: ");
            sleep(2000);

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract By getButtonLocator();

    public void voteLogging() {
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

    protected Document getPageSource() {
        return parse(webDriver.getPageSource());
    }

    protected abstract List<VoteCount> getVoteCountList(Document pageSource);

    public void voteClose() {
        String driverName = process.getDriverName();
        try {
            log.info(processName + " Завершаем работу драйвера: " + driverName);
            webDriver.quit();
            log.info(processName + " Работа драйвера " + driverName + " успешно завершена!");
        } catch (Exception e) {
            log.debug(processName + " При попытке завершить работу драйвера " + driverName + " возникла непредвиденная ошибка: " + e);
            log.info(processName + " Завершаем процесс драйвера: " + driverName);
            killProcess();
        }
    }

    private void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process.getDriverName());
        processKiller.killer(process.getProcessName());
    }
}
