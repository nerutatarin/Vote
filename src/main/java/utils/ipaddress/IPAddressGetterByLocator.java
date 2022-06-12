package utils.ipaddress;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class IPAddressGetterByLocator extends IPAddressGetter {

    public IPAddressGetterByLocator(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void response() {
        String ipAddressLocator = "#ipcontent > table > tbody > tr:nth-child(2) > td";
        String ip = webDriver.findElement(By.cssSelector(ipAddressLocator)).getText();
        IPAddress.setIp(ip);
    }

    @Override
    protected String getDefaultUrl() {
        return "https://myip.ru/";
    }
}
