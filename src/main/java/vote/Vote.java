package vote;

import org.apache.log4j.Logger;
import org.openqa.selenium.*;
import vote.browsers.*;

import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public abstract class Vote extends Thread implements VoteImpl {
    private static final Logger log = getLogger(Vote.class);
    protected PageManager pageManager;

    @Override
    public void run() {
        log.info("Начало работы...");
        for (int i = 0; i < getVoteCount(); i++) {
            init();
        }
    }

    protected abstract int getVoteCount();

    public void init() {
        try {
            List<Browsers> browsers = new ArrayList<>();
            browsers.add(new FirefoxBrowser());
            browsers.add(new EdgeBrowser());
            browsers.add(new ChromeBrowser());
            //browsers.add(new ChromiumBrowser());
            //browsers.add(new OperaBrowser());
            browsers.parallelStream().forEach(this::vote);
            //browsers.forEach(this::vote);
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
