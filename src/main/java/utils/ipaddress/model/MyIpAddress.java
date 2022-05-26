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

    @SerializedName("city")
    @Expose
    private String city;

    @SerializedName("hostname")
    @Expose
    private String hostname;

    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("org")
    @Expose
    private String org;

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

    public String getOrg() {
        return org;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setOrg(String cc) {
        this.org = org;
    }

    @Override
    public String toString() {
        return "MyIpAddress{" +
                "ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
