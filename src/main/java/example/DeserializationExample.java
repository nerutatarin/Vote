package example;

import org.apache.commons.io.IOUtils;
import org.apache.log4j.Logger;
import utils.Utils;
import vote.vote2022.kp.model.CookieKP;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

public class DeserializationExample {
    private static final Logger log = Logger.getLogger(DeserializationExample.class);

    public static void main(final String[] args) throws IOException {
        String fileName = "src/resources/gson_cookie_kp.json";

        /*Gson gson = new Gson();
        Type listType = new TypeToken<List<CookieKP>>() {}.getType();
        Object fromJson = gson.fromJson(loadFileFromClasspath(fileName), listType);*/

        //SetCookieKP cookieKPS = Utils.fileToObjectWithGson(fileName, SetCookieKP.class);


        /**
         * рабочая версия
         */
        CookieKP[] cookieKPS = Utils.fileToObjectWithGsonExposeMode(fileName, CookieKP[].class);
        Arrays.stream(cookieKPS).forEach(System.out::println);
    }

    private static String loadFileFromClasspath(String fileName) {
        ClassLoader classLoader = DeserializationExample.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream != null) {
                return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}