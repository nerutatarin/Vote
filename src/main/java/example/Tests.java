package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.configurations.Browser;
import service.configurations.BrowsersConfig;
import service.configurations.Options;
import service.configurations.ProxySettings;
import service.retrofit.api.myip.IpSeeipService;
import service.retrofit.api.myip.response.IPAddressInfo;
import service.telegrambot.TelegramBot;
import service.webdriver.browsers.Firefox;
import service.webdriver.model.Process;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;
import votes.kp.PageManagerKP;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.nio.file.Files.readAllLines;
import static java.nio.file.Paths.get;
import static utils.Thesaurus.DirectoriesName.DEFAULT_BASE_FILE_STORAGE_PATH;
import static utils.Thesaurus.DirectoriesName.UA_PATH;
import static utils.Thesaurus.Drivers.FIREFOX;
import static utils.Thesaurus.UserAgentsFiles.FIREFOX_UA;

public class Tests {
    private static final Logger log = Logger.getLogger(Tests.class);
    private static String JSON_PATH = DEFAULT_BASE_FILE_STORAGE_PATH + "json";
    private static Map<String, String[]> uaMap = new HashMap<String, String[]>();
    private static ChromeDriverService service;

    public static void main(String[] args) {
        RemoteWebDriver driver = null;
        try {
            createAndStartService();
            driver = createDriver();
            driver.get("ya.ru");
        } catch (IOException e) {
            driver.quit();
            stopService();
        }
    }

    private static void createAndStartService() throws IOException {
        service = new ChromeDriverService.Builder()
                .usingDriverExecutable(new File("src/resources/unix/chromedriver"))
                .usingAnyFreePort()
                .build();
        service.start();
    }

    private static void stopService() {
        service.stop();
    }

    private static RemoteWebDriver  createDriver() {
        return new RemoteWebDriver(service.getUrl(), new ChromeOptions());
    }

    private static void reader() {
        try {
            List<String> lines = readAllLines(get(UA_PATH + FIREFOX_UA), UTF_8);

            lines.stream().map(line -> new String[]{line + ","}).forEach(value -> uaMap.put(FIREFOX, value));

            System.out.println();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void TelegramBotInit() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void voteTest() {
        WebDriver webDriver = WebDriverManager.firefoxdriver().create();
        try {
            Process process = new Process();
            process.setBrowserName("firefox");
            PageManagerKP pageManagerKP = new PageManagerKP(webDriver, process);
            pageManagerKP.votePage("https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022");
            pageManagerKP.voteInput();
            pageManagerKP.voteButton();
        } catch (Exception e) {
            webDriver.close();
        } finally {
            webDriver.close();
        }
    }

    public static WebDriver getWebDriverNoProxy() {
        service.webdriver.Browser browser = new Firefox();
        return browser.getWebDriver();
    }

    public static WebDriver getWebDriverProxy() {
        service.webdriver.Browser browser = new Firefox();
        return browser.getWebDriver();
    }

    private static void yamlParserTest() {
        BrowsersConfig browsersConfig = new BrowsersConfig().parse();
        Map<String, Browser> browsersType = browsersConfig.getBrowserMap();
        Browser firefox = browsersType.get("firefox");
        System.out.println(firefox.getName());

        Options options = firefox.getOptions();
        System.out.println(options);

        boolean isHeadless = options.isHeadless();
        System.out.println(isHeadless);

        ProxySettings proxySettings = browsersConfig.getProxySettings();
        System.out.println(proxySettings);
    }

    private static void ipAddressGetterTest(WebDriver webDriver) {
        IPAddressGetter ipAddressGetter = new IPAddressGetterByJson(webDriver);
        IPAddress ipAddress = ipAddressGetter.getIpAddress();
        System.out.println(ipAddress);
    }

    private static void ipAddressApiService() {
        IpSeeipService ipSeeipService = new IpSeeipService();

        IPAddress ipJson = ipSeeipService.getIpJson();
        String ip = ipJson.getIp();
        System.out.println(ip);

        IPAddressInfo ipGeoip = ipSeeipService.getIpGeoip();
        String ipGeoipIp = ipGeoip.getIp();
        System.out.println(ipGeoipIp);
    }
}
