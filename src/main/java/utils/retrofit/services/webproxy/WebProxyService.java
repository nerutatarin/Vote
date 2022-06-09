package utils.retrofit.services.webproxy;

import org.apache.log4j.Logger;
import retrofit2.Call;
import utils.retrofit.BasicClient;
import utils.retrofit.services.webproxy.request.WebProxyRequest;
import utils.retrofit.services.webproxy.response.WebProxies;

import static org.apache.log4j.Logger.getLogger;

public class WebProxyService extends BasicClient {

    private static final Logger log = getLogger(WebProxyService.class);
    private final String baseUrl = "http://htmlweb.ru";
    private final WebProxyApi api = getApi(WebProxyApi.class, baseUrl);

    public WebProxies getProxyList() {
        WebProxyRequest webProxyRequest = new WebProxyRequest("RU", "100", "f82d703d19c2b2716fa901f5391a2aba");
        Call<WebProxies> response = api.getProxyList(webProxyRequest);
        return getResponse(response);
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
