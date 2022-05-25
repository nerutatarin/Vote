package utils.ipaddress;

import com.google.gson.Gson;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.model.Process;

import static org.apache.log4j.Logger.getLogger;
import static org.jsoup.Jsoup.parseBodyFragment;
import static org.openqa.selenium.By.cssSelector;

public class IPAddressGetter {
    private static final Logger log = getLogger(IPAddressGetter.class);
    private final WebDriver webDriver;
    private Process process;
    public String processName;

    private MyIpAddress myIpAddress = new MyIpAddress();

    public IPAddressGetter(WebDriver webDriver, Process process) {
        this.webDriver = webDriver;
        processName = process.getProcessName() + " ";
    }

    public MyIpAddress getIpAddressLocator(String ipAddressLocator, String url) {
        log.info(processName + "Получаем IP адрес... ");

        if (ipAddressLocator.isEmpty()) {
            ipAddressLocator = "#ipcontent > table > tbody > tr:nth-child(2) > td";
        }

        if (url.isEmpty()) {
            url = "https://myip.ru/";
        }

        try {
            webDriver.get(url);
            String ip = webDriver.findElement(cssSelector(ipAddressLocator)).getText();
            myIpAddress.setIp(ip);
            return myIpAddress;
        } catch (Exception e) {
            log.error("Превышено время ожидания загрузки страницы: " + e);
        }

        return null;
    }

    public MyIpAddress getIpAddressJson(String url) {
        log.info(processName + "Получаем IP адрес... ");

        if (url.isEmpty()) {
            url = "https://api.myip.com";
        }

        try {
            webDriver.get(url);
            String pageSource = webDriver.getPageSource();
            String document = parseBodyFragment(pageSource).text();
            Gson gson = new Gson();
            myIpAddress = gson.fromJson(document, MyIpAddress.class);
            return myIpAddress;
        } catch (Exception e) {
            log.error("Превышено время ожидания загрузки страницы: " + e);
        }

        return null;
    }

    public MyIpAddress getIpAddress(String url) {
        log.info(processName + "Получаем IP адрес... ");

        if (url.isEmpty()) {
            url = "https://api.ipify.org/";
        }

        try {
            webDriver.get(url);
            String pageSource = webDriver.getPageSource();
            Document document = parseBodyFragment(pageSource);
            myIpAddress.setIp(document.text());
            log.info(processName + "IP адрес " + myIpAddress.getIp());
            return myIpAddress;
        } catch (Exception e) {
            log.error("Превышено время ожидания загрузки страницы: " + e);
        }

        return null;
    }
}
