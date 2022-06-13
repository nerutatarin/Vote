package utils.retrofit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.ResponseBody;
import org.apache.log4j.Logger;
import org.jetbrains.annotations.Contract;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;
import service.configurations.ClientConfig;
import utils.Utils;

import java.io.IOException;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

public abstract class BasicClient {

    private ClientConfig clientConfig;
    protected Gson gson = buildGson();
    protected ObjectMapper objectMapper = buildObjectMapper();

    public BasicClient() {
    }

    public BasicClient(String url) {
        this(url, new ClientConfig());
    }

    public BasicClient(String url, ClientConfig config) {

    }

    protected <T> T getApi(Class<T> clazz, String baseUrl) {
        ClientConfig clientConfig = new ClientConfig();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(createGsonConverter())
                //.addConverterFactory(createJacksonConverter())
                .client(createClient(clientConfig, false))
                .build();
        return retrofit.create(clazz);
    }

    protected GsonConverterFactory createGsonConverter() {
        return GsonConverterFactory.create(gson);
    }
    protected JacksonConverterFactory createJacksonConverter() {
        return JacksonConverterFactory.create(objectMapper);
    }

    protected OkHttpClient createClient(ClientConfig config, boolean isIgnoringSSL) {
        OkHttpClient.Builder clientBuilder = new OkHttpClient().newBuilder()
                .connectTimeout(config.getConnectTimeout(), MILLISECONDS)
                .readTimeout(config.getReadTimeout(), MILLISECONDS);

        return clientBuilder.build();
    }

    private ObjectMapper buildObjectMapper() {
        return new ObjectMapper();
    }

    protected Gson buildGson() {
        return new GsonBuilder().create();
    }

    protected <T> T getResponse(Call<T> call) {
        return getOrThrowException(call, ResponseBody::string);
    }

    protected <T, U extends utils.retrofit.Response> T getResponse(Call<T> call, Class<U> errorModel) {
        return getOrThrowException(call, errorBody -> getApiErrorText(errorBody, errorModel));
    }

    private <T extends utils.retrofit.Response> String getApiErrorText(ResponseBody body, Class<T> model) throws IOException {
        String payload = body.string();
        if (!Utils.isBlankString(payload)) {
            try {
                utils.retrofit.Response response = gson.fromJson(payload, model);
                //utils.retrofit.Response response = objectMapper.readValue(payload, model);
                return response.getErrorMessage();
            } catch (Exception e) {
                getLog().error("deserialization failed: " + payload, e);
            }
        }
        return payload;
    }

    protected abstract Logger getLog();

    private <T> T getOrThrowException(Call<T> call, ErrorMessageExtractor extractor) {
        try {
            Response<T> response = call.execute();
            if (response.isSuccessful()) {
                return response.body();
            } else {
                return error(response, extractor);
            }
        } catch (IOException e) {
            Request request = call.request();
            String method = request.method() + " " + request.url().encodedPath();
            throw new RetrofitException("При обращении к сервису " + clientConfig.getName() + " (" + method + ") произошла ошибка: " + e.getMessage(), e);
        }
    }

    @Contract("_, _ -> fail")
    private <T> T error(Response<T> response, ErrorMessageExtractor extractor) throws IOException {

        Request request = response.raw().request();

        StringBuilder message = new StringBuilder()
                .append("Сервис ")
                .append(clientConfig.getName())
                .append(" вернул ошибку с кодом ")
                .append(response.code())
                .append(" на запрос '")
                .append(request.method())
                .append(" ")
                .append(request.url().encodedPath())
                .append("'");

        ResponseBody errorBody = response.errorBody();
        if (errorBody != null) {
            message.append(": ")
                    .append(extractor.extract(errorBody));
        }

        throw new RetrofitException(message.toString());
    }
}
