package utils.retrofit.services.apod.response;

import com.google.gson.annotations.SerializedName;

public class Apod {

    @SerializedName("copyright")
    public String copyright;

    @SerializedName("date")
    public String date;

    @SerializedName("explanation")
    public String explanation;

    @SerializedName("hdUrl")
    public String hdUrl;

    @SerializedName("mediaType")
    public String mediaType;

    @SerializedName("serviceVersion")
    public String serviceVersion;

    @SerializedName("title")
    public String title;

    @SerializedName("url")
    public String url;


    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }

    public String getHdUrl() {
        return hdUrl;
    }

    public void setHdUrl(String hdUrl) {
        this.hdUrl = hdUrl;
    }

    public String getMediaType() {
        return mediaType;
    }

    public void setMediaType(String mediaType) {
        this.mediaType = mediaType;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public void setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
