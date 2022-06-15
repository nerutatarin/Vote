package service.retrofit.api.freeproxy.response;

import com.google.gson.annotations.SerializedName;

public class CheckInfo {

    @SerializedName("isAlive")
    private boolean isAlive;

    @SerializedName("time")
    private String time;

    @SerializedName("miliseconds")
    private int miliseconds;

    @SerializedName("id")
    private int id;

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getMiliseconds() {
        return miliseconds;
    }

    public void setMiliseconds(int miliseconds) {
        this.miliseconds = miliseconds;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "CheckInfo{" +
                "isAlive=" + isAlive +
                ", time='" + time + '\'' +
                ", miliseconds=" + miliseconds +
                ", id=" + id +
                '}';
    }
}
