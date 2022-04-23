package vote.vote2022.browsers;

import org.openqa.selenium.WebDriver;
import vote.vote2022.browsers.model.BrowserProcess;

public interface BrowsersImpl {
    WebDriver getWebDriver();
    BrowserProcess getProcess();
}
