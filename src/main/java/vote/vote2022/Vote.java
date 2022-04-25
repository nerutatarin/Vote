package vote.vote2022;

import org.openqa.selenium.*;
import utils.IPAddressGetter;
import utils.ProcessKiller;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.FirefoxBrowser;
import vote.vote2022.browsers.model.BrowserProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.*;
import static org.openqa.selenium.By.id;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_VALUE;
import static utils.Thesaurus.SUBMIT_VOTE;

public abstract class Vote extends Thread implements VoteImpl {
    protected WebDriver webDriver;
    protected BrowserProcess process;

    @Override
    public void run() {
        out.println("Thread: " + getName());
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
            out.println("Невозможно создать сессию. Вероятно версия драйвера не совпадает с версией браузера : " + e);
        } catch (TimeoutException e) {
            out.println("Превышено время ожидания загрузки страницы: " + e);
        } catch (NoSuchElementException e) {
            out.println("Элемент полностью удален или больше не привязан к DOM.: " + e);
        } catch (NoSuchSessionException e) {
            out.println("Ошибка инициализации сеанса браузера: " + e);
        } catch (WebDriverException e) {
            out.println("Ошибка чтения страницы: " + e);
        } catch (Exception e) {
            out.println("Неизвестная ошибка: " + e);
        } finally {
            shutdown();
        }
    }

    public void writeToLog() {
        String ipAddress = getIpAddress();
        if (ipAddress == null) return;
        out.println("ipAddress: " + ipAddress);

        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
        String logFile = "src/resources/logs/" + "log.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(timeStamp + " ip: " + ipAddress + "\n");
        } catch (IOException e) {
            out.println("Ошибка операции ввода-вывода: " + e);
        }
    }

    private String getIpAddress() {
        IPAddressGetter ipAddressGetter = new IPAddressGetter(webDriver);
        return ipAddressGetter.getIpAddress(getCssSelector(), getMyIpUrl());
    }

    protected abstract String getMyIpUrl();

    protected abstract String getCssSelector();

    public void startPage() {
        out.println("Запуск страницы голосования");
        long startTime = nanoTime();
        out.println(startTime);

        webDriver.get(getBaseUrl());

        long estimatedTime = nanoTime() - startTime;
        double seconds = (double)estimatedTime / 1000000000.0;
        out.println(seconds);
        out.println("Запуск страницы завершен: ");
    }

    protected abstract String getBaseUrl();

    public void chkVoteMo() {
        for (String inp : inputs()) {
            WebElement webElement = webDriver.findElement(id(inp));
            webElement.click();
            out.println("Проставлен input: " + inp);
        }
    }

    protected abstract ArrayList<String> inputs();

    public void btnVote() {
        out.println("Нажимаем кнопку голосования: ");
        WebElement webElement = webDriver.findElement(id(SUBMIT_VOTE));
        webElement.click();
        out.println("Голоса приняты: ");
    }

    public void shutdown() {
        try {
            out.println("Закрываем браузер: ");
            webDriver.quit();
            out.println("Закрыт браузер: ");
        } catch (Exception e) {
            out.println("При попытке закрыть браузер возникла ошибка: " + e);
            out.println("Пытаемся прибить процесс...: ");
            killProcess();
        }
    }

    private void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process.getProcessName());
        processKiller.killer(process.getDriverName());
    }
}
