package utils.retrofit.services.webproxy.htmlweb;

import org.apache.log4j.Logger;
import utils.retrofit.BasicClient;
import utils.retrofit.services.webproxy.htmlweb.response.WebProxies;

import static org.apache.log4j.Logger.getLogger;

public class HtmlWebService extends BasicClient {

    private static final Logger log = getLogger(HtmlWebService.class);
    private final String baseUrl = "http://htmlweb.ru";
    private final HtmlWebApi api = getApi(HtmlWebApi.class, baseUrl);

    public WebProxies getProxyList() {
        String apiKey = "f82d703d19c2b2716fa901f5391a2aba";
        String perpage = "100";
        String country = "RU";

        /*WebProxyRequest webProxyRequest = new WebProxyRequest(country, perpage, apiKey);
        WebProxies response = getResponse(api.getWebProxies(webProxyRequest));*/
        return getResponse(api.getWebProxies(country, perpage, apiKey));
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
