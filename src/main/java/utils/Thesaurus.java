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

    public static class DirectoriesName {
        public static final String DEFAULT_BASE_LOG_PATH = "log/";
        public static final String DEFAULT_BASE_FILE_STORAGE_PATH = "filestorage/";
        public static final String JSON_PATH = DEFAULT_BASE_FILE_STORAGE_PATH + "json/";

        public static final String CONFIG_PATH = "configurations/";
    }

    public static class FilesNameJson {
        public static final String PAGE_AFTER_VOTING_JSON = "page_after_voting.json";
        public static final String PAGE_BEFORE_VOTING_JSON = "page_before_voting.json";
        public static final String COOKIE_AFTER_VOTING_JSON = "cookie_after_voting.json";
        public static final String COOKIE_BEFORE_VOTING_JSON = "cookie_before_voting.json";
    }

    public static class FilesNameYaml {
        public static final String VOTE_CONFIG_YAML = "vote_config.yaml";
        public static final String TELEGRAM_CONFIG_YAML = "telegram_config.yaml";
        public static final String BROWSER_CONFIG_YAML = "browser_config.yaml";
        public static final String MEMBER_CONFIG_YAML = "member_config.yaml";
    }

}
