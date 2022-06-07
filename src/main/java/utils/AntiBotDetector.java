package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;
import static org.openqa.selenium.By.xpath;

public class AntiBotDetector {
    private static final Logger log = getLogger(AntiBotDetector.class);
    private static final Duration timeout = Duration.ofSeconds(10);

    public static void webDriverModeDetected(WebDriver webDriver, String processName) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        webDriverModeDetected(webDriver, wait, processName);
    }

    public static void webDriverModeDetected(WebDriver webDriver, WebDriverWait wait, String processName) {
        try {
            String ANTIBOT_TEST_URL = "https://bot.sannysoft.com/";
            webDriver.get(ANTIBOT_TEST_URL);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(id("webdriver-result")));
            log.info(processName + " WebDriver mode detected: " + (webElement.getText().equals("present (failed)") ? "true" : "false"));
        } catch (TimeoutException e) {
            log.info(processName + " WebDriver mode detected: " + "fail detected");
        }
    }

    public static void secureDetected(WebDriver webDriver, String processName) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        secureDetected(webDriver, wait, processName);
    }

    public static void secureDetected(WebDriver webDriver, WebDriverWait wait, String processName) {
        try {
            String SECURE_DETECT_URL = "https://nowsecure.nl/";
            webDriver.get(SECURE_DETECT_URL);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(xpath("/html/body/div[2]/div/main/p[1]")));
            log.info(processName + " Secure detected: " + (webElement.getText().equals("you passed!") ? "true" : "false"));
        } catch (TimeoutException e) {
            log.info(processName + " Secure detected: " + "false");
        }
    }

    public static void headlessModeDetected(WebDriver webDriver, String processName) {
        WebDriverWait wait = new WebDriverWait(webDriver, timeout);
        headlessModeDetected(webDriver, wait, processName);
    }

    public static void headlessModeDetected(WebDriver webDriver, WebDriverWait wait, String processName) {
        try {
            String HEADLESSMODE_DETECT_URL = "https://arh.antoinevastel.com/bots/areyouheadless";
            webDriver.get(HEADLESSMODE_DETECT_URL);
            WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.className("success")));
            log.info(processName + " Headless mode detected: " + (webElement.getText().equals("You are not Chrome headless") ? "false" : "true"));
        } catch (TimeoutException e) {
            log.info(processName + " Headless mode detected: " + "fail detected");
        }
    }
}
