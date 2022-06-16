package service.webdriver;

import service.webdriver.browsers.*;

import java.util.HashMap;
import java.util.Map;

public class BrowserFactory {
    private static final Map<String, Browsers> browsersFactory = new HashMap<>();

    static {
        browsersFactory.put("chrome", new Chrome());
        browsersFactory.put("chromium", new Chromium());
        browsersFactory.put("firefox", new Firefox());
        browsersFactory.put("msedge", new MsEdge());
        browsersFactory.put("opera", new Opera());
    }

    public static Browsers getInstance(String text) {
        return browsersFactory.get(text);
    }
}
