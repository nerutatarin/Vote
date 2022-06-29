package utils;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static utils.Thesaurus.DirectoriesName.UA_PATH;
import static utils.Thesaurus.Drivers.*;
import static utils.Thesaurus.UserAgentsFiles.*;

public class RandomUserAgent {

    private static Map<String, String[]> uaMap = new HashMap<String, String[]>();
    private static Map<String, Double> freqMap = new HashMap<String, Double>();

    static {
        freqMap.put(IE, 11.8);
        freqMap.put(FIREFOX, 52.9);
        freqMap.put(CHROME, 28.2);
        freqMap.put(SAFARI, 3.9);
        freqMap.put(OPERA, 1.8);

        uaMap.put(IE, reader(IE_UA));
        uaMap.put(FIREFOX, reader(FIREFOX_UA));
        uaMap.put(CHROME, reader(CHROME_UA));
        uaMap.put(SAFARI, reader(SAFARI_UA));
        uaMap.put(OPERA, reader(OPERA_UA));
    }

    public static String getRandomUserAgent() {

        double rand = Math.random() * 100;
        String browser = null;
        double count = 0.0;
        for (Entry<String, Double> freq : freqMap.entrySet()) {
            count += freq.getValue();
            if (rand <= count) {
                browser = freq.getKey();
                break;
            }
        }

        if (browser == null) {
            browser = CHROME;
        }

        String[] userAgents = uaMap.get(browser);
        return userAgents[(int) Math.floor(Math.random() * userAgents.length)];
    }

    private static String[] reader(String fileName) {
        try {
            List<String> lines = readAllLines(get(UA_PATH + fileName), UTF_8);
            String[] value = new String[0];

            for (String line : lines) {
                value = new String[]{line};
            }
            return value;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}