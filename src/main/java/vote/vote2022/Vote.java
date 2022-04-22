package vote.vote2022;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxDriverLogLevel;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import utils.OSValidator;
import vote.vote2022.driver.Driver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static java.lang.System.out;
import static java.lang.System.setProperty;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.id;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;
import static utils.Thesaurus.SUBMIT_VOTE;

public abstract class Vote extends Thread implements VoteImpl {
    public WebDriver driver;
    private final Driver geckoDriver = new Driver();

    @Override
    public void run() {
        out.println("I'm Thread! My name is " + getName());
        init(1000);
    }

    public void init(int voteCount) {
        /*List<BrowsersImpl> browsers = new ArrayList<>();
        browsers.add(new FirefoxBrowser());
        browsers.add(new ChromeBrowser());*/

        for (int i = 0; i < voteCount; i++) {
            out.println("Начало работы: " + i);
            vote();
           // browsers.forEach(browser -> vote(browser.init()));
        }
    }

    protected void vote() {
//        this.driver = webDriver;
        try {
            geckoDriver.initGecko();
            getOptions();
            writeToLog();
            startPage(getBaseUrl(), "Запуск страницы голосования");
            chkVoteMo();
            btnVote();
        } catch (WebDriverException wde) {
            out.println("Произошла сетевая ошибка при инициализации сеанса WebDriver: " + wde.getMessage());
        } catch (Exception e) {
            out.println("Какая то ошибка: " + e.getMessage());
        } finally {
            try{
                shutdown();
            } catch (Exception e) {
                out.println("При попытке закрыть браузер возникла ошибка: " + e.getMessage());
                Runtime rt = Runtime.getRuntime();
                try {
                    Process p = rt.exec("killall geckodriver_0.31");
                    p.destroy();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }
        }
    }

    private void getOptions() {
        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("network.proxy.type", 1);
        firefoxProfile.setPreference("network.proxy.socks", "127.0.0.1");
        firefoxProfile.setPreference("network.proxy.socks_port", 9050);
        firefoxProfile.setPreference("network.proxy.socks_remote_dns", true);
        firefoxProfile.setPreference("toolkit.startup.max_resumed_crashes", "-1");
        firefoxProfile.setPreference("privacy.clearOnShutdown.cookies", true);
        firefoxProfile.setPreference("signon.rememberSignons", false);
        firefoxProfile.setPreference("network.cookie.lifetimePolicy", 2);
        firefoxProfile.setPreference("network.dns.disablePrefetch", true);
        firefoxProfile.setPreference("network.http.sendRefererHeader", 0);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setLogLevel(FirefoxDriverLogLevel.TRACE);
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setHeadless(true);
        firefoxOptions.setProfile(firefoxProfile);

        driver = new FirefoxDriver(firefoxOptions);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    public void writeToLog() {
        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
        String logFile = "src/resources/logs/" + "log.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {

            writer.write(timeStamp + " ip: " + getIpAddress() + "\n");

        } catch (IOException ex) {
            out.println(ex.getMessage());
        }
    }

    private String getIpAddress() {
        String cssSelector = "#ipcontent > table > tbody > tr:nth-child(2) > td";

        startPage(getMyIpUrl(), "Получаем IP");
        String ip = driver.findElement(cssSelector(cssSelector)).getText();

        out.println(ip);
        return ip;
    }

    protected abstract String getMyIpUrl();

    public void startPage(String url, String message) {
        out.println(message);
        driver.get(url);
        out.println("Запуск страницы завершен");
    }

    protected abstract String getBaseUrl();

    public void chkVoteMo() {
        for (String inp : inputs()) {
            driver.findElement(id(inp)).click();
            out.println("Проставлен input: " + inp);
        }
    }

    protected abstract ArrayList<String> inputs();

    public void btnVote() {
        out.println("Нажимаем кнопку голосования");
        driver.findElement(id(SUBMIT_VOTE)).click();
        out.println("Голоса приняты");
    }

    public void shutdown() {
        out.println("Закрываем браузер");
        driver.quit();
        out.println("Закрыт браузер");
    }
}
