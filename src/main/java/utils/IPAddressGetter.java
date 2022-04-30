package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.cssSelector;

public class IPAddressGetter {
    private static final Logger log = getLogger(IPAddressGetter.class);
    private WebDriver webDriver;

    public IPAddressGetter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getIpAddress(String ipAddressLocator, String url) {
        log.info("Получаем IP адрес... ");
        try {
            webDriver.get(url);
            return webDriver.findElement(cssSelector(ipAddressLocator)).getText();
        } catch (Exception e) {
            log.debug("Превышено время ожидания загрузки страницы: " + e);
        }
        return null;
    }
}
