package example;

import org.apache.log4j.Logger;
import utils.Utils;
import votes.kp.model.CookieKP;

import java.util.Arrays;

public class DeserializationExample {
    private static final Logger log = Logger.getLogger(DeserializationExample.class);

    public static void main(final String[] args) {
        String fileName = "src/resources/gson_cookie_kp.json";

        CookieKP[] cookieKPS = Utils.fileToObjectWithGsonExposeMode(fileName, CookieKP[].class);
        Arrays.stream(cookieKPS).forEach(System.out::println);
    }
}