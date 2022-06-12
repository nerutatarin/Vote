package utils.retrofit.services.myip;

import org.apache.log4j.Logger;
import utils.ipaddress.model.IPAddress;
import utils.retrofit.BasicClient;
import utils.retrofit.services.myip.response.IPAddressInfo;

import static org.apache.log4j.Logger.getLogger;

public class IpSeeipService extends BasicClient {
    private static final Logger log = getLogger(IpSeeipService.class);

    private final String baseUrl = "https://ip.seeip.org";
    private final IpSeeipApi api = getApi(IpSeeipApi.class, baseUrl);

    public IPAddress getIpJson() {
        return getResponse(api.getIpJson());
    }

    public IPAddressInfo getIpGeoip() {
        return getResponse(api.getIpGeoip());
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
