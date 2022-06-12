package vote.proxy;

import org.apache.log4j.Logger;
import org.openqa.selenium.Proxy;
import utils.configurations.browsers.ProxySettings;
import utils.retrofit.services.webproxy.freeproxy.FreeProxyService;
import utils.retrofit.services.webproxy.freeproxy.response.FreeProxyMedium;
import vote.browsers.model.Process;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class ProxyFactory {
    private static final Logger log = getLogger(ProxyFactory.class);

    private final ProxySettings proxySettings;
    private final String browserName;
    private final Proxy proxy = new Proxy();

    public ProxyFactory(ProxySettings proxySettings, String browserName) {
        this.proxySettings = proxySettings;
        this.browserName = browserName;
    }

    public Proxy getProxy(Process process) {
        if (!proxySettings.getProxyEnabled()) {
            noProxy();
        } else {
            if (proxySettings.getWebProxy()){
                webProxy(process);
            } else {
                torProxy();
            }
        }
        return proxy;
    }

    private void noProxy() {
        log.info(browserName + " Прокси выключен!");
        proxy.setNoProxy("");
    }

    private void webProxy(Process process) {
        FreeProxyMedium webProxy = getWebProxy();
        String host = webProxy.getHost();
        int port = webProxy.getPort();
        process.setHost(host);
        process.setPort(port);

        log.info(browserName + " Веб прокси включен: " + host + ":" + port + " " + webProxy.getType() + " " + " alive: " + webProxy.getAlive());
        if (webProxy.getType().contains("http")) {
            proxy.setHttpProxy(host + ":" + port);
        } else {
            proxy.setSocksProxy(host + ":" + port);
            proxy.setSocksVersion(Integer.parseInt(webProxy.getProxyTypeVersion()));
        }
    }

    private void torProxy() {
        log.info(browserName + " Тор прокси включен: " + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        proxy.setSocksProxy(PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        proxy.setSocksVersion(5);
    }

    private FreeProxyMedium getWebProxy() {
        FreeProxyService freeProxyService = new FreeProxyService();
        FreeProxyMedium proxyMedium = freeProxyService.getProxyMedium();
        return proxyMedium.getAlive() ? proxyMedium : getWebProxy();
    }
}
