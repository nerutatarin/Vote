package vote.vote2022;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.model.BrowserProcess;

import java.util.ArrayList;

import static org.apache.log4j.Logger.getLogger;
import static vote.vote2022.WriteToLog.writeToLog;

public abstract class Vote extends Thread implements VoteImpl {
    private static final Logger log = getLogger(Vote.class);

    protected WebDriver webDriver;
    protected BrowserProcess process;
    protected PageManager pageManager;

    @Override
    public void run() {
        init();
    }

    protected void vote(Browsers browser) {
        try {
            webDriver = browser.getWebDriver();
            process = browser.getProcess();
            pageManager = new PageManager(webDriver, process);
            pageManager.startPage(getBaseUrl());
            pageManager.chkVoteMo(getInputs());
            pageManager.btnVote();

            writeToLog(getIpAddress());
        } catch (SessionNotCreatedException e) {
            log.debug("Невозможно создать сессию. Вероятно версия драйвера не совпадает с версией браузера : " + e);
        } catch (TimeoutException e) {
            log.debug("Превышено время ожидания появления элемента. Либо что-то не так в сценарии, либо увеличить время для ожидания ответа: " + e);
        } catch (NoSuchElementException e) {
            log.debug("Не найден элемент. Или элемент реально отсутствует на странице или к нему не правильно указан доступ: " + e);
        } catch (NoSuchSessionException e) {
            log.debug("Ошибка инициализации сеанса браузера: " + e);
        } catch (WebDriverException e) {
            log.debug("Ошибка чтения страницы: " + e);
        } catch (Exception e) {
            log.debug("Неизвестная ошибка: " + e);
        } finally {
            pageManager.shutdown();
        }
    }

    protected abstract String getIpAddress();

    protected abstract String getMyIpUrl();

    protected abstract String getCssSelector();

    protected abstract String getBaseUrl();

    protected abstract ArrayList<String> getInputs();
}
