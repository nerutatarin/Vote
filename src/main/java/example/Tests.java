package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.configurations.BrowserConfig;
import service.configurations.Browsers;
import service.configurations.Options;
import service.configurations.ProxySettings;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;
import service.retrofit.api.myip.IpSeeipService;
import service.retrofit.api.myip.response.IPAddressInfo;
import service.telegrambot.TelegramBot;
import service.webdriver.browsers.Firefox;
import service.webdriver.model.Process;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;
import utils.jackson.JsonMapper;
import votes.kp.PageManagerKP;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Thesaurus.DirectoriesName.DEFAULT_BASE_FILE_STORAGE_PATH;

public class Tests {
    private static final Logger log = Logger.getLogger(Tests.class);
    private static String JSON_PATH = DEFAULT_BASE_FILE_STORAGE_PATH + "json";

    public static void main(String[] args) {
        VotingPage votingPage = JsonMapper.fileToObject("page_vote", VotingPage.class);
        Map<String, List<Member>> participantsMap = votingPage.getMembers();

        List<Member> result = participantsMap.values().stream().flatMap(Collection::stream).collect(Collectors.toList());

        System.out.println(result);
    }

    private static void TelegramBotInit() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void voteTest(){
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
        service.webdriver.Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

    public static WebDriver getWebDriverProxy() {
        service.webdriver.Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

    private static void yamlParserTest() {
        BrowserConfig browserConfig = new BrowserConfig().parse();
        Map<String, Browsers> browsersType = browserConfig.getBrowsers();
        Browsers firefox = browsersType.get("firefox");
        System.out.println(firefox.getName());

        Options options = firefox.getOptions();
        System.out.println(options);

        boolean isHeadless = options.isHeadless();
        System.out.println(isHeadless);

        ProxySettings proxySettings = browserConfig.getProxySettings();
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
