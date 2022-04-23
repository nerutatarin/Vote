package utils;

public class Thesaurus {

    public static class DateTimePatterns {
        public static final String PATTERN_DDMMYYYYHHMMSS = "dd-MM-yyyy HH:mm:ss";
    }

    public static final String SUBMIT_VOTE = "submit_vote";

    public static class ProxySettings {
        public static final String PROXY_IP_ADDRESS = "127.0.0.1";
        public static final int PROXY_PORT = 9050;
    }

    public static class Drivers {
        public static final String GECKO_DRIVER_KEY = "webdriver.gecko.driver";
        public static final String GECKO_DRIVER_VALUE = "geckodriver_0.31.0";

        public static final String CHROME_DRIVER_KEY = "webdriver.chrome.driver";
        public static final String CHROME_DRIVER_VALUE = "chromedriver_100.0.4896.60";
    }

    public static class MozCapabilities {
        public static final String MOZ_PROCESS_ID = "moz:processID";
    }
}
