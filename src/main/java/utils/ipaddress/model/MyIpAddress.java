package utils.ipaddress.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("jsonschema2pojo")
public class MyIpAddress {

    @SerializedName("ip")
    @Expose
    private String ip;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("cc")
    @Expose
    private String cc;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

}
