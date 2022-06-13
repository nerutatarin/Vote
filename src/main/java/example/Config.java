package example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.browsers.Browsers;
import service.browsers.Firefox;
import service.browsers.model.Process;
import service.configurations.BrowserProperties;
import service.configurations.BrowserType;
import service.configurations.Options;
import service.configurations.ProxySettings;
import service.pagemanager.model.ResultsVote;
import service.telegrambot.TelegramBot;
import utils.ipaddress.IPAddressGetter;
import utils.ipaddress.IPAddressGetterByJson;
import utils.ipaddress.model.IPAddress;
import utils.retrofit.services.myip.IpSeeipService;
import utils.retrofit.services.myip.response.IPAddressInfo;
import votes.kp.PageManagerKP;
import votes.kp.Results;

import java.util.List;
import java.util.Map;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public static void main(String[] args) {
        TelegramBotInit();
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

    private static void getResultsCount() {
        Results results = new Results();
        List<ResultsVote> resultsVoteList = results.getResults();
        System.out.println();
    }

    public static WebDriver getWebDriverNoProxy() {
        Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

    public static WebDriver getWebDriverProxy() {
        Browsers browser = new Firefox();
        return browser.getWebDriver();
    }

    private static void yamlParserTest() {
        BrowserProperties browserProperties = new BrowserProperties().parse();
        Map<String, BrowserType> browsersType = browserProperties.getBrowsersType();
        BrowserType firefox = browsersType.get("firefox");
        System.out.println(firefox.getName());

        Options options = firefox.getOptions();
        System.out.println(options);

        boolean isHeadless = options.isHeadless();
        System.out.println(isHeadless);

        ProxySettings proxySettings = browserProperties.getProxySettings();
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
