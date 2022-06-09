package utils.retrofit.services.webproxy.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.SerializedName;

public class WebProxies {

    @SerializedName("status")
    private int status;

    @SerializedName("limit")
    private int limit;

    @JsonIgnore
    private WebProxy webProxy;

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

}
