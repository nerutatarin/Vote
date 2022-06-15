package service.retrofit.api.myip;

import retrofit2.Call;
import retrofit2.http.GET;
import utils.ipaddress.model.IPAddress;
import service.retrofit.api.myip.response.IPAddressInfo;

public interface IpSeeipApi {

    /**
     * @return json    {"ip":"192.88.99.23"}
     */
    @GET("/json")
    Call<IPAddress> getIpJson();

    /**
     * @return {
     * "ip":"208.67.222.222",
     * "organization": "AS36692 OpenDNS, LLC",
     * "city": "San Francisco",
     * "region": "California",
     * "dma_code": "0",
     * "area_code": "0",
     * "timezone": "America\/Los_Angeles",
     * "offset": "-7",
     * "longitude": -122.3933,
     * "country_code3": "USA",
     * "postal_code": "94107",
     * "continent_code": "NA",
     * "country": "United States",
     * "region_code": "CA",
     * "country_code": "US","latitude":37.7697
     * }
     */
    @GET("/geoip")
    Call<IPAddressInfo> getIpGeoip();
}
