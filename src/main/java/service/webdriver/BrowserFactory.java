package service.webdriver;

import service.webdriver.browsers.*;

import java.util.LinkedHashMap;
import java.util.Map;

import static utils.Thesaurus.Drivers.*;

public class BrowserFactory {
    private static final Map<String, Browser> browsersFactory = new LinkedHashMap<>();

    static {
        browsersFactory.put(CHROMIUM, new Chromium());
        browsersFactory.put(CHROME, new Chrome());
        browsersFactory.put(FIREFOX, new Firefox());
        browsersFactory.put(MSEDGE, new MsEdge());
        browsersFactory.put(OPERA, new Opera());
    }

    public static Browser getInstance(String text) {
        return browsersFactory.get(text);
    }
}
