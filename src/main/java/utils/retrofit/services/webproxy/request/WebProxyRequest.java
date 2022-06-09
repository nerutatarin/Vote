package utils.retrofit.services.webproxy.request;

public class WebProxyRequest {
    private String country;
    private String perpage;
    private String api_key;

    public WebProxyRequest(String country, String perpage, String api_key) {
        this.country = country;
        this.perpage = perpage;
        this.api_key = api_key;
    }

    public String getCountry() {
        return country;
    }

    public String getPerpage() {
        return perpage;
    }

    public String getApi_key() {
        return api_key;
    }
}
