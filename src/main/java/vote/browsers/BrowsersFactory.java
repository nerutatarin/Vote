package vote.browsers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;

public class BrowsersFactory extends BrowsersImpl{

    public BrowsersFactory() {
    }

    public BrowsersFactory(boolean isHeadless) {
        super(isHeadless);
    }

    public WebDriver getBrowsersFactory(String browserName) {
        switch (browserName) {
            case ("chrome"):
                return new ChromeDriver(capabilities());
            case ("chromium"):
                return new ChromeDriver(capabilities());
            case ("firefox"):
                return new FirefoxDriver(capabilities());
            case ("msedge"):
                return new EdgeDriver(capabilities());
            case ("opera"):
                return new OperaDriver(capabilities());
            default:
                return new FirefoxDriver(capabilities());
        }
    }

    private Capabilities capabilities() {
        return getOptions();
    }

    protected <T> T getOptions() {
        return null;
    }
}
