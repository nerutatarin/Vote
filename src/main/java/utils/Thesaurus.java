package utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.asList;

public class Thesaurus {

    public static class DateTimePatterns {
        public static final String PATTERN_DDMMYYYYHHMMSS = "dd-MM-yyyy HH:mm:ss";
    }

    public static class ProxySettings {
        public static final String PROXY_IP_ADDRESS = "127.0.0.1";
        public static final int PROXY_PORT = 9050;
    }

    public static class Drivers {
        public static final String FIREFOX = "firefox";
        public static final String GECKO_DRIVER_KEY = "webdriver.gecko.driver";
        public static final String GECKO_DRIVER_VALUE = "geckodriver";

        public static final String CHROME = "chrome";
        public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
        public static final String CHROME_DRIVER_VALUE = "chromedriver";

        public static final String OPERA = "opera";
        public static final String OPERA_DRIVER_KEY = "webdriver.opera.driver";
        public static final String OPERA_DRIVER_VALUE = "operadriver";

        public static final String MSEDGE = "msedge";
        public static final String MSEDGE_DRIVER_KEY = "webdriver.edge.driver";
        public static final String MSEDGE_DRIVER_VALUE = "msedgedriver";

        public static final Map<String, String> DRIVERS_MAP = new HashMap<>();

        static {
            DRIVERS_MAP.put(FIREFOX, GECKO_DRIVER_VALUE);
            DRIVERS_MAP.put(CHROME, CHROME_DRIVER_VALUE);
            DRIVERS_MAP.put(OPERA, OPERA_DRIVER_VALUE);
            DRIVERS_MAP.put(MSEDGE, MSEDGE_DRIVER_VALUE);
        }

        public static final List<String> DRIVERS_LIST = asList(GECKO_DRIVER_VALUE, CHROME_DRIVER_VALUE, OPERA_DRIVER_VALUE, MSEDGE_DRIVER_VALUE);
        public static final List<String> BROWSERS_LIST = asList(FIREFOX, CHROME, OPERA, MSEDGE);
    }

    public static class Capabilities {
        public static final String MOZ_PROCESS_ID = "moz:processID";
        public static final String MOZ_GECKODRIVER_VERSION = "moz:geckodriverVersion";
    }
}
