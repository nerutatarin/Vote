package service.proxy;

import service.configurations.ProxySettings;

import java.util.HashMap;
import java.util.Map;

import static service.proxy.ModeProxyEnum.*;

public class ProxiesFactory {
    private static final Map<String, Proxy> proxyFactory = new HashMap<>();

    static {
        proxyFactory.put(NO_PROXY.getValue(), new NoProxy());
        proxyFactory.put(WEB_PROXY.getValue(), new WebProxy());
        proxyFactory.put(TOR_PROXY.getValue(), new TorProxy());
    }

    public static Proxy getInstance(ProxySettings proxySettings) {
        return proxyFactory.get(getModeProxy(proxySettings));
    }

    private static String getModeProxy(ProxySettings proxySettings) {

        if (proxySettings.getNoProxy()) return NO_PROXY.getValue();

        if (proxySettings.getTorProxy()) return TOR_PROXY.getValue();

        if (proxySettings.getWebProxy()) return WEB_PROXY.getValue();

        return NO_PROXY.getValue();
    }
}
