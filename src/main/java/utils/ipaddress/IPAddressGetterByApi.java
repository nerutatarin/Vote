package utils.ipaddress;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;

public class IPAddressGetterByApi extends IPAddressGetter {

    public IPAddressGetterByApi(WebDriver webDriver) {
        super(webDriver);
    }

    @Override
    protected void response() {
        String pageSource = webDriver.getPageSource();
        Document document = Jsoup.parseBodyFragment(pageSource);
        IPAddress.setIp(document.text());
    }

    @Override
    protected String getDefaultUrl() {
        return "https://api.ipify.org/";
    }
}
