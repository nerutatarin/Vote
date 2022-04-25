package vote.vote2022;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ProcessKiller;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.EdgeBrowser;
import vote.vote2022.browsers.model.BrowserProcess;

import java.util.ArrayList;
import java.util.List;

import static java.lang.System.nanoTime;
import static java.time.Duration.ofSeconds;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static utils.Thesaurus.SUBMIT_VOTE;

public class PageManager{
    private static final Logger log = getLogger(PageManager.class);

    private final WebDriverWait wait;
    private final WebDriver webDriver;
    private final BrowserProcess process;
    private long startTime;

    public PageManager(WebDriver webDriver, BrowserProcess process) {
        this.webDriver = webDriver;
        this.process = process;
        wait = new WebDriverWait(webDriver, ofSeconds(30));
        long startTime = nanoTime();
        //Timeouts wait = webDriver.manage().timeouts().implicitlyWait(60, SECONDS);
    }

    public void startPage(String baseUrl) {
        log.info("Запуск страницы голосования");
/*
        try {
            webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
            webDriver.get(getBaseUrl());
        } catch (TimeoutException ignore) {
        }
        webDriver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);*/
        webDriver.get(baseUrl);

        double seconds = ((double) nanoTime() - startTime) / 1000000000.0;
        log.info("загрузка страницы завершена за: " + seconds + " сек.");
    }

    public void chkVoteMo(ArrayList<String> inputs) {
        /*try {
         *//*for (String inp : inputs()) {
         *//**//*WebDriverWait wait = new WebDriverWait(webDriver, 10);*//*
            WebDriverWait wait = (WebDriverWait) webDriver.manage().timeouts().implicitlyWait(60, SECONDS);
            WebElement webElement = wait.until(visibilityOfElementLocated(By.id(inp)));
            webElement.click();
            log.info("Проставлен input: " + inp);
        } catch (TimeoutException ignore) {
        }*/
        for (String inp : inputs) {
            log.info("Ищем input: " + inp + " ...");
            WebElement webElement = wait.until(visibilityOfElementLocated(By.id(inp)));
            //WebElement webElement = webDriver.findElement(id(inp));
            webElement.click();
            log.info("Проставлен input: " + inp);
        }
    }

    public void btnVote() {
        /*WebDriverWait wait = (WebDriverWait) webDriver.manage().timeouts().implicitlyWait(60, SECONDS);
        WebElement buttonVote = wait.until(visibilityOfElementLocated(By.id(SUBMIT_VOTE)));
        try {
            buttonVote.click();
            log.info("Голоса приняты: ");
        } catch (TimeoutException ignore) {
        }
        wait.until(stalenessOf(buttonVote));*/
        log.info("Ищем кнопку голосования: ");
        WebElement buttonVote = wait.until(visibilityOfElementLocated(By.id(SUBMIT_VOTE)));
        //WebElement buttonVote = webDriver.findElement(id(SUBMIT_VOTE));
        log.info("Кнопка голосования нажата: ");
    }

    public void shutdown() {
        try {
            log.info("Закрываем браузер: ");
            webDriver.quit();
            log.info("Закрыт браузер: ");
        } catch (Exception e) {
            log.debug("При попытке закрыть браузер возникла ошибка: " + e);
            log.info("Пытаемся прибить процесс...: ");
            killProcess();
        }
    }

    private void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process.getProcessName());
        processKiller.killer(process.getDriverName());
    }
}
