package utils.retrofit.services.apod;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import utils.retrofit.services.apod.response.Apod;

public interface ApodApi {
    @GET("/planetary/apod")
    @Headers("accept: application/json")
    Call<Apod> getApod(@Query("api_key") String apiKey);
}
