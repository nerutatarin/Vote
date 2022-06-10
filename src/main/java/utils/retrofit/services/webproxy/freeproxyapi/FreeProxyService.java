package utils.retrofit.services.webproxy.freeproxyapi;

import org.apache.log4j.Logger;
import utils.retrofit.BasicClient;
import utils.retrofit.services.webproxy.freeproxyapi.response.FreeProxyMini;

import static org.apache.log4j.Logger.getLogger;

public class FreeProxyService extends BasicClient {
    private static final Logger log = getLogger(FreeProxyService.class);
    private final String baseUrl = "https://public.freeproxyapi.com/api";
    private final FreeProxyApi api = getApi(FreeProxyApi.class, baseUrl);

    public FreeProxyMini getProxyMini() {
        return getResponse(api.getMini());
    }

    public FreeProxyMini getProxyMedium() {
        return getResponse(api.getMedium());
    }

    public FreeProxyMini getProxyLarge() {
        return getResponse(api.getLarge());
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
