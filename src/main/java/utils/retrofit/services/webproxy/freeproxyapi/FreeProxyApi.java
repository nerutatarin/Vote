package utils.retrofit.services.webproxy.freeproxyapi;

import retrofit2.Call;
import retrofit2.http.GET;
import utils.retrofit.services.webproxy.freeproxyapi.response.FreeProxyMedium;
import utils.retrofit.services.webproxy.freeproxyapi.response.FreeProxyMini;

public interface FreeProxyApi {

    /**
     * {
     * "host": "139.198.121.69",
     * "port": 5678,
     * "type": "Socks4",
     * "proxyLevel": "Elite"
     * }
     *
     * @return
     */
    @GET("/Proxy/mini")
    Call<FreeProxyMini> getMini();

    /**
     * {
     * "isAlive": true,
     * "miliseconds": 9560,
     * "averageTime": 9559,
     * "countryName": "Poland",
     * "latitude": 51.6448,
     * "longitude": 17.8151,
     * "host": "94.154.31.136",
     * "port": 2114,
     * "type": "Socks4",
     * "proxyLevel": "Elite"
     * }
     *
     * @return
     */
    @GET("/Proxy/medium")
    Call<FreeProxyMedium> getMedium();

    /**
     * {
     * "totalUpdated": 12,
     * "totalFailed": 27,
     * "checkInfo": [
     * {
     * "isAlive": false,
     * "time": "2022-06-10T04:08:48.2926984+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T04:37:25.742601+00:00",
     * "miliseconds": 67495,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T05:16:45.7832574+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T05:38:16.0527731+00:00",
     * "miliseconds": 33651,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T06:12:28.4911525+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T06:44:18.1512959+00:00",
     * "miliseconds": 12357,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T07:19:25.018892+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T07:53:18.1491635+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T08:24:33.9929605+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T08:52:14.144342+00:00",
     * "miliseconds": 68442,
     * "id": 0
     * }
     * ],
     * "continentCode": "EU",
     * "continentName": "Europe",
     * "countryCode": "RU",
     * "cityName": "Moscow",
     * "isInEu": true,
     * "isAlive": true,
     * "miliseconds": 19075,
     * "averageTime": 19075,
     * "countryName": "Russia",
     * "latitude": 55.7483,
     * "longitude": 37.6171,
     * "host": "188.133.158.145",
     * "port": 8080,
     * "type": "Http",
     * "proxyLevel": "NotValidated"
     * }
     *
     * @return
     */
    @GET("/Proxy/large")
    Call<FreeProxyMini> getLarge();
}
