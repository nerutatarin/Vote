package utils.ipaddress.model;

import com.google.gson.annotations.SerializedName;

public class IPAddress {

    @SerializedName("ip")
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    @SerializedName("country")
    private String country;

    @SerializedName("city")
    private String city;

    @SerializedName("hostname")
    private String hostname;

    @SerializedName("region")
    private String region;

    @SerializedName("org")
    private String org;

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
        return "IPAddress{" +
                "ip='" + ip + '\'' +
                ", country='" + country + '\'' +
                ", city='" + city + '\'' +
                ", hostname='" + hostname + '\'' +
                ", region='" + region + '\'' +
                ", org='" + org + '\'' +
                '}';
    }
}
