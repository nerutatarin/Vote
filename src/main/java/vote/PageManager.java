package vote;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.ProcessKiller;
import vote.browsers.model.BrowserProcess;
import vote.vote2022.kp.PageManagerKP;

import java.util.ArrayList;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static java.util.concurrent.TimeUnit.SECONDS;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public abstract class PageManager implements PageManagerImpl {
    private static final Logger log = getLogger(PageManagerKP.class);

    public WebDriverWait wait;
    public WebDriver webDriver;
    public BrowserProcess process;

    public void startPage(String baseUrl) {
        wait = new WebDriverWait(webDriver, ofSeconds(30));
        webDriver.manage().timeouts().implicitlyWait(30, SECONDS);
        //webDriver.manage().timeouts().pageLoadTimeout(10, SECONDS);
        log.info("Запуск страницы голосования");
        webDriver.get(baseUrl);
    }

    public void chkVoteMo(ArrayList<String> inputs) {
        for (String inp : inputs) {
            log.info("Ищем input: " + inp + " ...");
            WebElement webElement = wait.until(visibilityOfElementLocated(By.id(inp)));
            webElement.click();
            log.info("Проставлен input: " + inp);
        }
    }

    public void btnVote() {
        log.info("Ищем кнопку голосования: ");
        //WebElement webElement = wait.until(visibilityOfElementLocated(getLocator()));
        WebElement webElement = wait.until(ExpectedConditions.elementToBeClickable(getLocator()));
        webElement.click();
        log.info("Кнопка голосования нажата: ");
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract By getLocator();

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

    public void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process.getProcessName());
        processKiller.killer(process.getDriverName());
    }
}
