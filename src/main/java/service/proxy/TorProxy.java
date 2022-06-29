package service.proxy;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class TorProxy implements Proxy {
    private static final Logger log = getLogger(TorProxy.class);

    @Override
    public org.openqa.selenium.Proxy getProxy(String browserName) {
        log.info(browserName + " Тор прокси включен: " + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        proxy.setSocksProxy(PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        proxy.setSocksVersion(5);
        return proxy;
    }
}
