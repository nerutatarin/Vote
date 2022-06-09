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


    public WebProxy(String name, int work, String type, int speed, String upd, String country) {
        this.name = name;
        this.work = work;
        this.type = type;
        this.speed = speed;
        this.upd = upd;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public int getWork() {
        return work;
    }

    public String getType() {
        return type;
    }

    public int getSpeed() {
        return speed;
    }

    public String getUpd() {
        return upd;
    }

    public String getCountry() {
        return country;
    }
}
