package service.webdriver;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import service.webdriver.model.Process;

public interface Browsers {

    WebDriver getWebDriver();

    String getBrowserName();

    Capabilities getCapabilities();

    Process getProcess();

    void webDriverClose();
}
