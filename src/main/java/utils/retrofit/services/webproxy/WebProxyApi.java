package utils.retrofit.services.webproxy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import utils.retrofit.services.webproxy.request.WebProxyRequest;
import utils.retrofit.services.webproxy.response.WebProxies;

public interface WebProxyApi {
    @FormUrlEncoded
    @POST("/json/proxy/get")
    Call<WebProxies> getProxyList(
            @Field("country") String country,
            @Field("perpage") String perpage,
            @Field("api_key") String api_key
    );

    Call<WebProxies> getProxyList(@Body WebProxyRequest webProxyRequest);
}
