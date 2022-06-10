package utils.retrofit.services.webproxy.freeproxy.response;

import com.google.gson.annotations.SerializedName;

public class FreeProxyMini {
    @SerializedName("host")
    private String host;
    @SerializedName("port")
    private int port;
    @SerializedName("type")
    private String type;
    @SerializedName("proxyLevel")
    private String proxyLevel;

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getProxyLevel() {
        return proxyLevel;
    }

    public void setProxyLevel(String proxyLevel) {
        this.proxyLevel = proxyLevel;
    }

    @Override
    public String toString() {
        return "FreeProxyMini{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", type='" + type + '\'' +
                ", proxyLevel='" + proxyLevel + '\'' +
                '}';
    }
}
