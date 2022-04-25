package vote.vote2022;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import utils.IPAddressGetter;
import utils.ProcessKiller;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.model.BrowserProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static java.lang.System.nanoTime;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;
import static utils.Thesaurus.SUBMIT_VOTE;

public abstract class Vote extends Thread implements VoteImpl {
    private static final Logger log = getLogger(Vote.class);

    protected WebDriver webDriver;
    protected BrowserProcess process;

    @Override
    public void run() {
        init();
    }

    protected void vote(Browsers browser) {
        try {
            webDriver = browser.getWebDriver();
            process = browser.getProcess();
            startPage();
            chkVoteMo();
            btnVote();
            writeToLog();
        } catch (SessionNotCreatedException e) {
            log.debug("Невозможно создать сессию. Вероятно версия драйвера не совпадает с версией браузера : " + e);
        } catch (TimeoutException e) {
            log.debug("Превышено время ожидания загрузки страницы: " + e);
        } catch (NoSuchElementException e) {
            log.debug("Элемент полностью удален или больше не привязан к DOM.: " + e);
        } catch (NoSuchSessionException e) {
            log.debug("Ошибка инициализации сеанса браузера: " + e);
        } catch (WebDriverException e) {
            log.debug("Ошибка чтения страницы: " + e);
        } catch (Exception e) {
            log.debug("Неизвестная ошибка: " + e);
        } finally {
            shutdown();
        }
    }

    public void writeToLog() {
        String ipAddress = getIpAddress();
        if (ipAddress == null) return;
        log.info("ipAddress: " + ipAddress);

        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
        String logFile = "src/resources/logs/" + "log.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(timeStamp + " ip: " + ipAddress + "\n");
        } catch (IOException e) {
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }

    private String getIpAddress() {
        IPAddressGetter ipAddressGetter = new IPAddressGetter(webDriver);
        return ipAddressGetter.getIpAddress(getCssSelector(), getMyIpUrl());
    }

    protected abstract String getMyIpUrl();

    protected abstract String getCssSelector();

    public void startPage() {
        log.info("Запуск страницы голосования");
        long startTime = nanoTime();
        webDriver.get(getBaseUrl());
        long estimatedTime = nanoTime() - startTime;
        double seconds = (double) estimatedTime / 1000000000.0;
        log.info(seconds);
        log.info("Запуск страницы завершен: ");
    }

    protected abstract String getBaseUrl();

    public void chkVoteMo() {
        for (String inp : inputs()) {
            WebElement webElement = webDriver.findElement(id(inp));
            webElement.click();
            log.info("Проставлен input: " + inp);
        }
    }

    protected abstract ArrayList<String> inputs();

    public void btnVote() {
        log.info("Нажимаем кнопку голосования: ");
        WebElement webElement = webDriver.findElement(id(SUBMIT_VOTE));
        webElement.click();
        log.info("Голоса приняты: ");
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
