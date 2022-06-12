package utils.ipaddress;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import utils.ipaddress.model.IPAddress;

import static org.apache.log4j.Logger.getLogger;

public abstract class IPAddressGetter {
    private static final Logger log = getLogger(IPAddressGetter.class);

    protected final WebDriver webDriver;
    protected IPAddress IPAddress = new IPAddress();
    protected String browserName;

    public IPAddressGetter(WebDriver webDriver) {
        this.webDriver = webDriver;
        browserName = ((RemoteWebDriver) webDriver).getCapabilities().getBrowserName();
    }

    public IPAddress getIpAddress() {
        return getIpAddress(getDefaultUrl());
    }

    public IPAddress getIpAddress(String url) {
        log.info(browserName + " Получаем IP адрес... ");


        url = validUrl(url);

        try {
            webDriver.get(url);

            response();

            log.info(browserName + " IP адрес = " + IPAddress.getIp());
            return IPAddress;
        } catch (Exception e) {
            log.error(browserName + " Не удалось получить IP адрес ");
        }

        return IPAddress;
    }

    protected abstract void response();

    @NotNull
    private String validUrl(String url) {
        if (url.isEmpty()) {
            url = getDefaultUrl();
        }
        return url;
    }

    protected abstract String getDefaultUrl();

}
