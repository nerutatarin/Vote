package utils.retrofit.services.webproxy.response;

import com.google.gson.annotations.SerializedName;

public class WebProxy {

    @SerializedName("name")
    private String name;

    @SerializedName("work")
    private int work;

    @SerializedName("type")
    private String type;

    @SerializedName("speed")
    private int speed;

    @SerializedName("upd")
    private String upd;

    @SerializedName("country")
    private String country;

    public WebProxy() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWork() {
        return work;
    }

    public void setWork(int work) {
        this.work = work;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getUpd() {
        return upd;
    }

    public void setUpd(String upd) {
        this.upd = upd;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
