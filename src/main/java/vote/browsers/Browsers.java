package vote.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import vote.browsers.model.Process;

public interface Browsers {

    WebDriver getWebDriver();

    String getBrowserName();

    Capabilities getCapabilities();

    Process getProcess();

    void webDriverClose();
}
