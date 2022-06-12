package utils.ipaddress;

import com.google.gson.Gson;
import org.jsoup.Jsoup;
import org.openqa.selenium.WebDriver;
import utils.ipaddress.model.MyIpAddress;

public class IPAddressGetterByJson extends IPAddressGetter {

    public IPAddressGetterByJson(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void response() {
        String pageSource = webDriver.getPageSource();
        String document = Jsoup.parseBodyFragment(pageSource).text();

        Gson gson = new Gson();
        myIpAddress = gson.fromJson(document, MyIpAddress.class);
    }

    @Override
    protected String getDefaultUrl() {
        return "https://ipinfo.io/?token=c2e2408c951023";
    }
}
