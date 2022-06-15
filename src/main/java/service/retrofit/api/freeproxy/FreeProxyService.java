package service.retrofit.api.freeproxy;

import org.apache.log4j.Logger;
import service.retrofit.BasicClient;
import service.retrofit.api.freeproxy.response.FreeProxyLarge;
import service.retrofit.api.freeproxy.response.FreeProxyMedium;
import service.retrofit.api.freeproxy.response.FreeProxyMini;

import static org.apache.log4j.Logger.getLogger;

/**
 * Этот API можно использовать бесплатно, если в месяц поступает не более 100 000 запросов и только один запрос за раз.
 * Каждый запрос, выполняемый параллельно, будет заблокирован.
 */
public class FreeProxyService extends BasicClient {
    private static final Logger log = getLogger(FreeProxyService.class);
    private final String baseUrl = "https://public.freeproxyapi.com";
    private final FreeProxyApi api = getApi(FreeProxyApi.class, baseUrl);

    public FreeProxyMini getProxyMini() {
        return getResponse(api.getMini());
    }

    public FreeProxyMedium getProxyMedium() {
        return getResponse(api.getMedium());
    }

    public FreeProxyLarge getProxyLarge() {
        return getResponse(api.getLarge());
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
