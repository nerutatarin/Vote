package utils.retrofit.services.webproxy.response;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.Map;

public class WebProxies {

    @SerializedName("status")
    private int status;

    @SerializedName("limit")
    private int limit;

    @JsonAnySetter
    private Map<String, WebProxy> webProxies = new HashMap<>();

    public WebProxies() {
    }

    @SerializedName("balans")
    private int balans;

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

    public int getBalans() {
        return balans;
    }

    public void setBalans(int balans) {
        this.balans = balans;
    }

    public Map<String, WebProxy> getWebProxies() {
        return webProxies;
    }

    public void setWebProxies(Map<String, WebProxy> webProxies) {
        this.webProxies = webProxies;
    }
}
