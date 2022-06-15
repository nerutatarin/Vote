package service.retrofit.api.apod;

import org.apache.log4j.Logger;
import retrofit2.Call;
import service.retrofit.BasicClient;
import service.retrofit.api.apod.response.Apod;

import static org.apache.log4j.Logger.getLogger;

public class ApodService extends BasicClient {
    private static final Logger log = getLogger(ApodService.class);
    private final String baseUrl = "https://api.nasa.gov";
    private final ApodApi api = getApi(ApodApi.class, baseUrl);

    public Apod getTitle() {
        Call<Apod> response = api.getApod("DEMO_KEY");
        return getResponse(response);
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
