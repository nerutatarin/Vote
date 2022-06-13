package service.configurations.browsers;

public class ProxySettings {
    private boolean proxyEnabled;
    private boolean torProxy;
    private boolean webProxy;

    public boolean getProxyEnabled() {
        return proxyEnabled;
    }

    public void setProxyEnabled(boolean proxyEnabled) {
        this.proxyEnabled = proxyEnabled;
    }

    public boolean getTorProxy() {
        return torProxy;
    }

    public void setTorProxy(boolean torProxy) {
        this.torProxy = torProxy;
    }

    public boolean getWebProxy() {
        return webProxy;
    }

    public void setWebProxy(boolean webProxy) {
        this.webProxy = webProxy;
    }

    @Override
    public String toString() {
        return "ProxySettings{" +
                "proxyEnabled=" + proxyEnabled +
                ", torProxy=" + torProxy +
                ", webProxy=" + webProxy +
                '}';
    }
}
