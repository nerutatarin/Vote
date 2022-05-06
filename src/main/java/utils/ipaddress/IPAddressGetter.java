package utils.ipaddress;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import utils.ipaddress.model.MyIpAddress;

import static org.apache.log4j.Logger.getLogger;
import static org.jsoup.Jsoup.parse;
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

    public String getIpAddress(String url) {
        log.info("Получаем IP адрес... ");
        try {
            webDriver.get(url);
            String document = parse(webDriver.getPageSource()).body().text();
            Gson gson = new Gson();
            MyIpAddress response = gson.fromJson(document, MyIpAddress.class);
            String ip = response.getIp();
            log.info("Ip Address = " + ip);
            return ip;
        } catch (Exception e) {
            log.debug("Превышено время ожидания загрузки страницы: " + e);
        }
        return null;
    }
}
