package utils.ipaddress;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import utils.ipaddress.model.IPAddress;

public class IPAddressGetterByJson extends IPAddressGetter {

    public static final String url = "https://ip.seeip.org/geoip";
    public static final String url2 = "https://ipinfo.io/?token=c2e2408c951023";

    public IPAddressGetterByJson(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void response() {
        String pageSource = webDriver.getPageSource();
        String document = Jsoup.parseBodyFragment(pageSource).text();

        Gson gson = new Gson();
        IPAddress = gson.fromJson(document, IPAddress.class);
    }

    @Override
    protected String getDefaultUrl() {
        return url;
    }
}
