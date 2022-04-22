package vote.vote2021;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    private static FirefoxDriver driver;
    private static FirefoxOptions options;
    private static Process torProcess;
    private static FirefoxProfile profile;
    static String voteMo = "ГБУЗ РБ Городская клиническая больница № 21 г. Уфа";
    public static String getVoteMo() {        return voteMo;    }
    public static void setVoteMo(String voteMo) {        Main.voteMo = voteMo;    }
    static String baseUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2021";
    public static String getBaseUrl() {        return baseUrl;    }
    public static void setBaseUrl(String baseUrl) {        Main.baseUrl = baseUrl;    }

    public static void main(String[] args) throws IOException {

        settingBrowser();
        driver.get(getBaseUrl());
        chkVoteMo(getVoteMo());
        btnVote();
        getVoteCount();
        //driver.close();
        driver.quit();
        killFirefox();
    }

    private static void settingBrowser() throws IOException {

        String driverPath = "src/resources/geckodriver.exe";
        String torPath = "src/resources/Tor Browser/Browser/firefox.exe";
        System.setProperty("webdriver.gecko.driver", driverPath);
        Runtime runTime = Runtime.getRuntime();
        torProcess = runTime.exec(torPath + " -n");
        profile = new FirefoxProfile();
        profile.setPreference("network.proxy.type", 1);
        profile.setPreference("network.proxy.socks", "127.0.0.1");
        profile.setPreference("network.proxy.socks_port", 9150);
        profile.setPreference("toolkit.startup.max_resumed_crashes", 9999);
        profile.setPreference("privacy.clearOnShutdown.cookies", true);

        options = new FirefoxOptions();
        options.setProfile(profile);

        driver = new FirefoxDriver(options);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        //WebDriverWait wait = new WebDriverWait(driver, 30);

    }

    private static void chkVoteMo(String voteMo) {
        List<WebElement> list = driver.findElements(By.xpath("//label[@for]"));
        for (WebElement el : list) {
            String elText = el.getText();
            if (elText.contains(voteMo)) {
                String inputNumber = el.getAttribute("for");
                WebElement chkInput = driver.findElement(By.id(inputNumber));
                if (!chkInput.isSelected()) {
                    chkInput.click();
                }
            }
        }
        /*============================================*/
        //List<WebElement> list = driver.findElements(By.cssSelector("div.answer>label"));
        /*List<WebElement> list = driver.findElements(By.xpath("//label[@for]"));
        for (int i = 0; i < list.size(); i++) {
            String txtList = list.get(i).getText();
            if (txtList.contains(voteMo)) {
                String inputNumber = list.get(i).getAttribute("for");
                WebElement chkInput = driver.findElement(By.id(inputNumber));
                if (!chkInput.isSelected()) {
                    chkInput.click();
                }
            }
        }*/
    }

    private static void btnVote() {
        WebElement btnVote = driver.findElement(By.id("submit_vote"));
        btnVote.click();
    }

    private static void getVoteCount() throws IOException {
        Document page = getpage();
        Element blockVote = page.select("div[class=all_poll-new]").first();
        Elements names = blockVote.select("div[class=questiondata]");
        //System.out.println(names);

        Elements Elements = names.select(
                "div.unicredit_poll_results_block > span.unicredit_poll_results_answer"
        );

        for (Element E : Elements) {

            System.out.println(E.attr("unicredit_poll_results_count"));
        }

        /*List<WebElement> listResultMo = driver.findElements(By.className("unicredit_poll_results_block"));
        for (WebElement elMo : listResultMo) {
            String elTextMo = elMo.getText();
            if (elTextMo.contains(voteMo)) {
                String elTextCount = elMo.findElement(By.className("unicredit_poll_results_count")).getText();
                System.out.println(voteMo + " - " + elTextCount);
            }
        }*/
    }

    private static void killFirefox() {
        Runtime rt = Runtime.getRuntime();
        try {
            rt.exec("taskkill /F /IM firefox.exe");
            while (processIsRunning("firefox.exe")) {
                Thread.sleep(100);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static boolean processIsRunning(String process) {
        boolean processIsRunning = false;
        String line;
        try {
            Process proc = Runtime.getRuntime().exec("wmic.exe");
            BufferedReader input = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            OutputStreamWriter oStream = new OutputStreamWriter(proc.getOutputStream());
            oStream.write("process where name='" + process + "'");
            oStream.flush();
            oStream.close();
            while ((line = input.readLine()) != null) {
                if (line.toLowerCase().contains("caption")) {
                    processIsRunning = true;
                    break;
                }
            }
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return processIsRunning;

    }

    private static Document getpage() throws IOException {
        Document page = Jsoup.parse(driver.getPageSource());
        return page;
    }
}
