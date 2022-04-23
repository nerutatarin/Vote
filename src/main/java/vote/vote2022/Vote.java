package vote.vote2022;

import org.openqa.selenium.WebDriver;
import utils.IPAddressGetter;
import utils.ProcessKiller;
import vote.vote2022.browsers.model.BrowserProcess;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.System.out;
import static org.openqa.selenium.By.id;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;
import static utils.Thesaurus.SUBMIT_VOTE;

public abstract class Vote extends Thread implements VoteImpl {
    protected WebDriver webDriver;
    protected BrowserProcess process;

    @Override
    public void run() {
        out.println("Thread: " + getName());
        init(1);
    }

    public void init(int voteCount) {
        for (int i = 0; i < voteCount; i++) {
            out.println("Начало работы: " + i);
            vote();
        }
    }

    protected void vote() {
        try {
            writeToLog();
            startPage(getBaseUrl(), "Запуск страницы голосования");
            chkVoteMo();
            btnVote();
        } catch (Exception e) {
            out.println("Какая то ошибка: " + e.getMessage());
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
        } catch (IOException ex) {
            out.println(ex.getMessage());
        }
    }

    private String getIpAddress() {
        IPAddressGetter ipAddressGetter = new IPAddressGetter(webDriver);
        return ipAddressGetter.getIpAddress(getCssSelector(), getMyIpUrl());
    }

    protected abstract String getMyIpUrl();

    protected abstract String getCssSelector();

    public void startPage(String url, String message) {
        out.println(message);
        webDriver.get(url);
        out.println("Запуск страницы завершен: ");
    }

    protected abstract String getBaseUrl();

    public void chkVoteMo() {
        for (String inp : inputs()) {
            webDriver.findElement(id(inp)).click();
            out.println("Проставлен input: " + inp);
        }
    }

    protected abstract ArrayList<String> inputs();

    public void btnVote() {
        out.println("Нажимаем кнопку голосования: ");
        webDriver.findElement(id(SUBMIT_VOTE)).click();
        out.println("Голоса приняты: ");
    }

    public void shutdown() {
        try {
            out.println("Закрываем браузер: ");
            webDriver.quit();
            out.println("Закрыт браузер: ");
        } catch (Exception e) {
            out.println("При попытке закрыть браузер возникла ошибка: " + e.getMessage());
            out.println("Пытаемся прибить процесс...: ");
            killProcess();
        }
    }

    private void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process);
    }
}
