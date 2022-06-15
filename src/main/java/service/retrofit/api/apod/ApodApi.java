package service.retrofit.api.apod;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import service.retrofit.api.apod.response.Apod;

public interface ApodApi {
    @GET("/planetary/apod")
    @Headers("accept: application/json")
    Call<Apod> getApod(@Query("api_key") String apiKey);
}
