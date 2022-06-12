package vote;

import org.openqa.selenium.WebDriver;
import vote.browsers.model.Process;

public interface Vote {
    void vote(WebDriver driver, Process process);
}
