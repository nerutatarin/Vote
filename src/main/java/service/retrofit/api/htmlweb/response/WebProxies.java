package service.retrofit.api.htmlweb.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.google.gson.annotations.SerializedName;

import java.util.LinkedHashMap;
import java.util.Map;

public class WebProxies {

    private final Map<String, WebProxy> proxies = new LinkedHashMap<>();

    @SerializedName("status")
    //@JsonProperty("status")
    private int status;

    @SerializedName("limit")
    //@JsonProperty("limit")
    private int limit;

    @SerializedName("balans")
    //@JsonProperty("balans")
    private int balanse;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getBalanse() {
        return balanse;
    }

    public void setBalanse(int balanse) {
        this.balanse = balanse;
    }

    @JsonAnySetter
    public void add(String key, WebProxy proxy) {
        proxies.put(key, proxy);
    }

    public Map<String, WebProxy> getProxies() {
        return proxies;
    }

    @Override
    public String toString() {
        return "WebProxies{" +
                "proxyMap=" + proxies +
                ", status=" + status +
                ", limit=" + limit +
                ", balanse=" + balanse +
                '}';
    }
}
