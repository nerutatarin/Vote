package vote.pagemanager;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.IPAddressGetter;
import utils.ProcessKiller;
import vote.browsers.model.Process;
import vote.vote2022.kp.PageManagerKP;

import java.util.List;

import static java.lang.Thread.sleep;
import static java.time.Duration.ofSeconds;
import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public abstract class PageManagerImpl implements PageManager {
    private static final Logger log = getLogger(PageManagerKP.class);

    public WebDriverWait wait;
    public WebDriver webDriver;
    public Process process;

    public void votePage(String baseUrl) {
        wait = new WebDriverWait(webDriver, ofSeconds(20));
        log.info("Запуск страницы голосования " + baseUrl);
        webDriver.get(baseUrl);
    }

    public void voteInput() {
        getInputsListLocatorById().forEach(inp -> {
            log.info("Ищем input: " + inp + " ...");
            WebElement webElement = wait.until(elementToBeClickable(id(inp)));
            webElement.click();
            log.info("Проставлен input: " + inp);
        });
    }

    protected abstract List<String> getInputsListLocatorById();

    public void voteButton() {

        log.info("Ищем кнопку голосования: ");

        WebElement webElement = wait.until(elementToBeClickable(getButtonLocator()));

        webElement.click();

        log.info("Кнопка голосования нажата: ");

        try {
            sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract By getButtonLocator();

    public String getIpAddress(String myIpUrl) {
        IPAddressGetter ipAddressGetter = new IPAddressGetter(webDriver);
        return ipAddressGetter.getIpAddress(getIpAddressLocator(), myIpUrl);
    }

    protected abstract String getIpAddressLocator();

    public void voteClose() {
        String driverName = process.getDriverName();
        try {
            log.info("Завершаем работу драйвера: " + driverName);
            webDriver.quit();
            log.info("Работа драйвера " + driverName + " успешно завершена!");
        } catch (Exception e) {
            log.debug("При попытке завершить работу драйвера " + driverName + " возникла непредвиденная ошибка: " + e);
            log.info("Завершаем процесс драйвера: " + driverName);
            killProcess();
        }
    }

    private void killProcess() {
        ProcessKiller processKiller = new ProcessKiller();
        processKiller.killer(process.getDriverName());
        processKiller.killer(process.getProcessName());
    }
}
