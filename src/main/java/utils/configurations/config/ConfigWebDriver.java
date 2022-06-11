package utils.configurations.config;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConfigWebDriver {

    private BrowserProperties browserType;

    public BrowserProperties getBrowserType() {
        return browserType;
    }

    public void setBrowserType(BrowserProperties browserType) {
        this.browserType = browserType;
    }
}
