package votes;

import org.openqa.selenium.WebDriver;
import service.browsers.model.Process;

public interface Vote {
    void vote(WebDriver driver, Process process);
}
