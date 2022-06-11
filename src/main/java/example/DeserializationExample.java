package example;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.log4j.Logger;
import utils.retrofit.services.webproxy.htmlweb.response.WebProxies;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeserializationExample {
    private static final Logger log = Logger.getLogger(DeserializationExample.class);

    public static void main(final String[] args) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        Gson gson = new Gson().newBuilder().create();

        Reader reader = null;
        try {
            reader = Files.newBufferedReader(Paths.get("src/resources/response.json"));

            /*Map<?, ?> map = gson.fromJson(reader, Map.class);
            String asString = objectMapper.writeValueAsString(map);*/

            //WebProxies webProxies = objectMapper.readValue(reader, WebProxies.class);
            WebProxies webProxies = gson.fromJson(reader, WebProxies.class);

            log.info(webProxies.toString());
        } catch (IOException e) {
            reader.close();
            throw new RuntimeException(e);
        }
    }
}