package service.proxy;

import org.apache.log4j.Logger;

import static org.apache.log4j.Logger.getLogger;

public class NoProxy implements Proxy {
    private static final Logger log = getLogger(NoProxy.class);

    @Override
    public org.openqa.selenium.Proxy getProxy(String browserName) {
        log.info(browserName + " Прокси выключен!");
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        return proxy.setNoProxy("");
    }
}
