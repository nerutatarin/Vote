package utils.retrofit.services.webproxy.freeproxy.response;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class FreeProxyLarge extends FreeProxyMedium{

    @SerializedName("totalUpdated")
    private int totalUpdated;

    @SerializedName("totalFailed")
    private int totalFailed;

    @SerializedName("checkInfo")
    private List<CheckInfo> checkInfo = new ArrayList<>();

    @SerializedName("continentCode")
    private String continentCode;

    @SerializedName("continentName")
    private String continentName;

    @SerializedName("countryCode")
    private String countryCode;

    @SerializedName("cityName")
    private String cityName;

    @SerializedName("isInEu")
    private boolean isInEu;

    public int getTotalUpdated() {
        return totalUpdated;
    }

    public void setTotalUpdated(int totalUpdated) {
        this.totalUpdated = totalUpdated;
    }

    public int getTotalFailed() {
        return totalFailed;
    }

    public void setTotalFailed(int totalFailed) {
        this.totalFailed = totalFailed;
    }

    public List<CheckInfo> getCheckInfo() {
        return checkInfo;
    }

    public void setCheckInfo(List<CheckInfo> checkInfo) {
        this.checkInfo = checkInfo;
    }

    public String getContinentCode() {
        return continentCode;
    }

    public void setContinentCode(String continentCode) {
        this.continentCode = continentCode;
    }

    public String getContinentName() {
        return continentName;
    }

    public void setContinentName(String continentName) {
        this.continentName = continentName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public boolean getInEu() {
        return isInEu;
    }

    public void setInEu(boolean inEu) {
        isInEu = inEu;
    }

    @Override
    public String toString() {
        return "FreeProxyLarge{" +
                "totalUpdated=" + totalUpdated +
                ", totalFailed=" + totalFailed +
                ", checkInfo=" + checkInfo +
                ", continentCode='" + continentCode + '\'' +
                ", continentName='" + continentName + '\'' +
                ", countryCode='" + countryCode + '\'' +
                ", cityName='" + cityName + '\'' +
                ", isInEu=" + isInEu +
                '}';
    }
}
