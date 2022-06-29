package service.proxy;

import org.apache.log4j.Logger;
import service.retrofit.api.freeproxy.FreeProxyService;
import service.retrofit.api.freeproxy.response.FreeProxyMedium;

import static org.apache.log4j.Logger.getLogger;

public class WebProxy implements Proxy{
    private static final Logger log = getLogger(WebProxy.class);

    @Override
    public org.openqa.selenium.Proxy getProxy(String browserName) {
        FreeProxyMedium webProxy = getWebProxy();
        String host = webProxy.getHost();
        int port = webProxy.getPort();

        log.info(browserName + " Веб прокси включен: " + host + ":" + port + " " + webProxy.getType() + " " + " alive: " + webProxy.getAlive());
        org.openqa.selenium.Proxy proxy = new org.openqa.selenium.Proxy();
        if (webProxy.getType().contains("http")) {
            proxy.setHttpProxy(host + ":" + port);
        } else {
            proxy.setSocksProxy(host + ":" + port);
            proxy.setSocksVersion(Integer.parseInt(webProxy.getProxyTypeVersion()));
        }
        return proxy;
    }

    private FreeProxyMedium getWebProxy() {
        FreeProxyService freeProxyService = new FreeProxyService();
        FreeProxyMedium proxyMedium = freeProxyService.getProxyMedium();
        return proxyMedium.getAlive() ? proxyMedium : getWebProxy();
    }
}
