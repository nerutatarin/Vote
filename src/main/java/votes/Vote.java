package votes;

import org.openqa.selenium.WebDriver;
import service.webdriver.model.Process;

public interface Vote {
    void vote(WebDriver driver, Process process);
}
