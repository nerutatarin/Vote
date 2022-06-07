package utils.configurations;

import java.util.HashMap;
import java.util.Map;

public class ConfigWebDriver {
    private Map<String, BrowserType> browserTypeMap = new HashMap<>();

    public ConfigWebDriver() {
    }

    public Map<String, BrowserType> getBrowserTypeMap() {
        return browserTypeMap;
    }

    public void setBrowserTypeMap(Map<String, BrowserType> browserTypeMap) {
        this.browserTypeMap = browserTypeMap;
    }

    @Override
    public String toString() {
        return "ConfigWebDriver{" +
                "browserTypeMap=" + browserTypeMap +
                '}';
    }
}
