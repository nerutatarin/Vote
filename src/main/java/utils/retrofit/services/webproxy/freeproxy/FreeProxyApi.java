package utils.retrofit.services.webproxy.freeproxy;

import retrofit2.Call;
import retrofit2.http.GET;
import utils.retrofit.services.webproxy.freeproxy.response.FreeProxyLarge;
import utils.retrofit.services.webproxy.freeproxy.response.FreeProxyMedium;
import utils.retrofit.services.webproxy.freeproxy.response.FreeProxyMini;

public interface FreeProxyApi {

    /**
     * @return {
     * "host": "139.198.121.69",
     * "port": 5678,
     * "type": "Socks4",
     * "proxyLevel": "Elite"
     * }
     */
    @GET("/api/Proxy/mini")
    Call<FreeProxyMini> getMini();

    /**
     * @return {
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
     */
    @GET("/api/Proxy/medium")
    Call<FreeProxyMedium> getMedium();

    /**
     * @return {
     * "totalUpdated": 104,
     * "totalFailed": 63,
     * "checkInfo": [
     * {
     * "isAlive": true,
     * "time": "2022-06-10T03:48:59.1653363+00:00",
     * "miliseconds": 110,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T04:21:51.8822179+00:00",
     * "miliseconds": 64100,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T04:54:13.1131655+00:00",
     * "miliseconds": 7173,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T05:32:47.4639262+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T05:53:04.9926259+00:00",
     * "miliseconds": 1116,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T06:27:22.80408+00:00",
     * "miliseconds": 7176,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T07:01:45.7969008+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T07:36:34.4430557+00:00",
     * "miliseconds": 0,
     * "id": 0
     * },
     * {
     * "isAlive": true,
     * "time": "2022-06-10T08:03:08.2436446+00:00",
     * "miliseconds": 280,
     * "id": 0
     * },
     * {
     * "isAlive": false,
     * "time": "2022-06-10T08:41:23.1440111+00:00",
     * "miliseconds": 0,
     * "id": 0
     * }
     * ],
     * "continentCode": "AS",
     * "continentName": "Asia",
     * "countryCode": "TR",
     * "cityName": "Istanbul",
     * "isInEu": false,
     * "isAlive": false,
     * "miliseconds": 14099,
     * "averageTime": 14099,
     * "countryName": "Turkey",
     * "latitude": 41.0082,
     * "longitude": 28.9784,
     * "host": "84.51.56.123",
     * "port": 4145,
     * "type": "Socks4",
     * "proxyLevel": "NotValidated"
     * }
     */
    @GET("/api/Proxy/large")
    Call<FreeProxyLarge> getLarge();
}
