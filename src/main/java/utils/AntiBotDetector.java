package utils;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.apache.log4j.Logger.getLogger;
import static org.openqa.selenium.By.id;

public class AntiBotDetector {
    private static final Logger log = getLogger(AntiBotDetector.class);

    public static void webDriverModeDetected(WebDriver webDriver, WebDriverWait wait, String processName) {
        String ANTIBOT_TEST_URL = "https://bot.sannysoft.com/";
        webDriver.get(ANTIBOT_TEST_URL);
        WebElement webElement = wait.until(ExpectedConditions.presenceOfElementLocated(id("webdriver-result")));
        log.info(processName + " WebDriverMode detected? " + (webElement.getText().equals("present (failed)") ? "true" : "false"));
    }
}
