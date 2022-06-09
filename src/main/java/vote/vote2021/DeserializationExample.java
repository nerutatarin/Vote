package vote.vote2021;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import utils.retrofit.services.webproxy.response.WebProxies;
import utils.retrofit.services.webproxy.response.WebProxy;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Map;

public class DeserializationExample {
    public static void main(final String[] args) throws IOException {
        Gson gson = new GsonBuilder().create();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/resources/response.json"));

            WebProxies map = gson.fromJson(reader, WebProxies.class);
            Map<String, WebProxy> webProxies = map.getWebProxies();

            System.out.println();
        } catch (IOException e) {
            reader.close();
            throw new RuntimeException(e);
        }
    }
}