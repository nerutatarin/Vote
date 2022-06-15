package service.retrofit.api.htmlweb;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Query;
import service.retrofit.api.htmlweb.request.WebProxyRequest;
import service.retrofit.api.htmlweb.response.WebProxies;

public interface HtmlWebApi {

    @GET("/json/proxy/get")
    Call<WebProxies> getWebProxies(
            @Query("country") String country,
            @Query("perpage") String perpage,
            @Query("api_key") String api_key
    );

    @FormUrlEncoded
    @GET
    Call<WebProxies> getWebProxies(@Body WebProxyRequest webProxyRequest);
}
