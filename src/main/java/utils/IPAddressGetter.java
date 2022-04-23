package utils;

import org.openqa.selenium.WebDriver;

import static java.lang.System.out;
import static org.openqa.selenium.By.cssSelector;

public class IPAddressGetter {
    private final WebDriver webDriver;

    public IPAddressGetter(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    public String getIpAddress(String cssSelector, String url) {
        out.println("Получаем IP адрес... ");
        try {
            webDriver.get(url);
            return webDriver.findElement(cssSelector(cssSelector)).getText();
        } catch (Exception e) {
            out.println("Превышено время ожидания загрузки страницы: " + e);
        }
        return null;
    }
}
