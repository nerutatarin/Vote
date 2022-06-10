package vote.browsers;

import org.openqa.selenium.WebDriver;
import vote.browsers.model.Process;

public interface Browsers {

    WebDriver getWebDriver();

    Process getProcess();

    String getInstanceName();

    void voteClose();
}
