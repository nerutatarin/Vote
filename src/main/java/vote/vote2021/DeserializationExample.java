package vote.vote2021;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

            /*Type type = new TypeToken<Map<String, WebProxies>>(){}.getType();
            Map<String, WebProxies> json = gson.fromJson(reader, type);*/

            Map<?, ?> map = gson.fromJson(reader, Map.class);
            System.out.println();
        } catch (IOException e) {
            reader.close();
            throw new RuntimeException(e);
        }
    }
}