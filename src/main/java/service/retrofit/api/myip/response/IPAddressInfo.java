package service.retrofit.api.myip.response;

import com.google.gson.annotations.SerializedName;
import utils.ipaddress.model.IPAddress;

public class IPAddressInfo extends IPAddress {

    @SerializedName("country_code")
    private String country_code;

    @SerializedName("country_code3")
    private String country_code3;

    @SerializedName("region_code")
    private String region_code;

    @SerializedName("postal_code")
    private String postal_code;

    @SerializedName("continent_code")
    private String continent_code;

    @SerializedName("latitude")
    private String latitude;

    @SerializedName("longitude")
    private String longitude;

    @SerializedName("dma_code")
    private String dma_code;

    @SerializedName("area_code")
    private String area_code;

    @SerializedName("organization")
    private String organization;

    @SerializedName("timezone")
    private String timezone;

    @SerializedName("offset")
    private String offset;

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getCountry_code3() {
        return country_code3;
    }

    public void setCountry_code3(String country_code3) {
        this.country_code3 = country_code3;
    }

    public String getRegion_code() {
        return region_code;
    }

    public void setRegion_code(String region_code) {
        this.region_code = region_code;
    }

    public String getPostal_code() {
        return postal_code;
    }

    public void setPostal_code(String postal_code) {
        this.postal_code = postal_code;
    }

    public String getContinent_code() {
        return continent_code;
    }

    public void setContinent_code(String continent_code) {
        this.continent_code = continent_code;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getDma_code() {
        return dma_code;
    }

    public void setDma_code(String dma_code) {
        this.dma_code = dma_code;
    }

    public String getArea_code() {
        return area_code;
    }

    public void setArea_code(String area_code) {
        this.area_code = area_code;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getTimezone() {
        return timezone;
    }

    public void setTimezone(String timezone) {
        this.timezone = timezone;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    @Override
    public String toString() {
        return "IPAddressInfo{" +
                "country_code='" + country_code + '\'' +
                ", country_code3='" + country_code3 + '\'' +
                ", region_code='" + region_code + '\'' +
                ", postal_code='" + postal_code + '\'' +
                ", continent_code='" + continent_code + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", dma_code='" + dma_code + '\'' +
                ", area_code='" + area_code + '\'' +
                ", organization='" + organization + '\'' +
                ", timezone='" + timezone + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
