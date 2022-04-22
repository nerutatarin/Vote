package vote.vote2021.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class Main {

    private static Process torProcess;
    private static WebDriver firefoxDriver;

    public static void main(String[] args) {
        String url = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2021";
        int voteAmount = 10000;

        vote(url, voteAmount);
    }

    public static void vote(String url, int voteAmount) {
        for (int i = 0; i < voteAmount; i++) {
            System.out.println("Start job: " + i);
            initVote(url);
        }
    }

    public static void initVote(String url) {
        try {
            initGecko();
            initTor();
            settingBrowser();
            startBrowserAndPage(url);
           /* clickInputs();
            clickBtnVote();*/
        } catch (Exception e) {
            System.out.println("WTF: " + e.getMessage());
        } finally {
            shutdown();
        }
    }

    public static void initGecko() {
        //String geckoDriverPath = "src/resources/geckodriver_old";
        String geckoDriverPath = "C:\\Users\\zaripov\\IdeaProjects\\Vote\\src\\main\\resources\\geckodriver_0.28.0.exe";
        try {
            System.setProperty("webdriver.gecko.driver", geckoDriverPath);
            System.out.println("Init Gecko driver...");
        } catch (Exception e) {
            System.out.println("Wrong init Gecko driver: " + e);
        }
    }

    public static void initTor() {
        try {
            //String torPath = "src/resources/Tor_Browser/Browser/firefox";
            String torPath = "C:\\Users\\zaripov\\IdeaProjects\\Vote\\src\\main\\resources\\Tor_Browser\\Browser\\firefox.exe";
            System.out.println("Init Tor...");
            torProcess = Runtime.getRuntime().exec(torPath + " -n" + " -headless");
            //torProcess = Runtime.getRuntime().exec(torPath + " -n");
        } catch (Exception e) {
            System.out.println("Problem start Tor: " + e);
        }
    }

    public static void settingBrowser() {
        System.out.println("Setting Firefox...");

        FirefoxProfile firefoxProfile = new FirefoxProfile();
        firefoxProfile.setPreference("network.proxy.type", 1);
        firefoxProfile.setPreference("network.proxy.socks", "127.0.0.1");
        firefoxProfile.setPreference("network.proxy.socks_port", 9150);
        firefoxProfile.setPreference("network.proxy.socks_remote_dns", true);
        firefoxProfile.setPreference("toolkit.startup.max_resumed_crashes", "-1");
        firefoxProfile.setPreference("privacy.clearOnShutdown.cookies", true);

        FirefoxOptions firefoxOptions = new FirefoxOptions();
        firefoxOptions.setAcceptInsecureCerts(true);
        firefoxOptions.setHeadless(true);
        firefoxOptions.setProfile(firefoxProfile);

        firefoxDriver = new FirefoxDriver(firefoxOptions);
        firefoxDriver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        System.out.println("Setting Firefox complete!");

    }

    public static void startBrowserAndPage(String url) {
        try {
            System.out.println("Start page");
            firefoxDriver.get(url);
            System.out.println("Start page complete");
        } catch (Exception e) {
            System.out.println("Wrong Start page: " + e);
        }
    }

    public static void checkAvailabilitySite() {
        String title = firefoxDriver.findElement(By.className("title")).getText();
        System.out.println("title: " + title);
        while ("Update server".equals(title)) {
            try {
                System.out.println("Server update, timeout 10 min...");
                Thread.sleep(600000);
                firefoxDriver.navigate().refresh();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }

   /* public static void clickInputs() {
        try {
            for (String inp : getInputs()) {
                WebDriverWait wait = new WebDriverWait(firefoxDriver, 10);
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(inp))).click();
                //firefoxDriver.findElement(By.id(inp)).click();
                System.out.println("Check input: " + inp);
            }
        } catch (NoSuchElementException e) {
            System.out.println("Inputs not found: " + e);
        }
    }*/

    public static ArrayList<String> getInputs() {
        ArrayList<String> inputs = new ArrayList<>();
        inputs.add("inp3");     //21
        //inputs.add("inp11");    //
        inputs.add("inp20");    //
        //inputs.add("inp25");    //
        inputs.add("inp27");    //21
        //inputs.add("inp29");    //
        inputs.add("inp51");    //21
        inputs.add("inp56");    //21
        inputs.add("inp61");    //21
        inputs.add("inp64");    //21
        inputs.add("inp72");    //21
        return inputs;
    }

    /*public static void clickBtnVote() {
        WebDriverWait wait = new WebDriverWait(firefoxDriver, 10);
        WebElement btnVote = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("submit_vote")));
        try {
            if (btnVote.isDisplayed()) {
                btnVote.submit();
                System.out.println("Click button vote");

            }
        } catch (NoSuchElementException e) {
            System.out.println("button vote not found: " + e);
        }
    }*/

    public static void shutdown() {
        try {
            Thread.sleep(5000);
            firefoxDriver.quit();
            System.out.println("Close Firefox");
        } catch (Exception e) {
            System.out.println("Wrong close Firefox: " + e);
        }
        try {
            Thread.sleep(5000);
            killFirefox();
            torProcess.destroy();
            System.out.println("Close Tor");
        } catch (Exception e) {
            killFirefox();
            System.out.println("Wrong close Tor: " + e);
        }
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
}
