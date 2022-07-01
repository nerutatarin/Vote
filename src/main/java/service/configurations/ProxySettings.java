package service.configurations;

public class ProxySettings {
    private boolean noProxy;
    private boolean torProxy;
    private boolean webProxy;

    public boolean getNoProxy() {
        return noProxy;
    }

    public void setNoProxy(boolean noProxy) {
        this.noProxy = noProxy;
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
                "noProxy=" + noProxy +
                ", torProxy=" + torProxy +
                ", webProxy=" + webProxy +
                '}';
    }
}
