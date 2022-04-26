package vote.browsers;

import org.openqa.selenium.WebDriver;
import vote.browsers.model.BrowserProcess;

public interface BrowsersImpl {
    WebDriver getWebDriver();
    BrowserProcess getProcess();
}
