package service.retrofit.api.htmlweb.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WebProxy {

    @JsonProperty("name")
    private String name;

    @JsonProperty("work")
    private int work;

    @JsonProperty("type")
    private String type;

    @JsonProperty("speed")
    private int speed;

    @JsonProperty("upd")
    private String upd;

    @JsonProperty("country")
    private String country;

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
