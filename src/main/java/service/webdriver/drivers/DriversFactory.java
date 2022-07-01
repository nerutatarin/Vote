package service.webdriver.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import service.webdriver.BrowsersImpl;

public abstract class DriversFactory extends BrowsersImpl {
    public WebDriver getBrowsersFactory(String browserName) {
        switch (browserName) {
            case ("chrome"):
                return new ChromeDriver(getOptions());
            case ("chromium"):
                return new ChromeDriver(getOptions());
            case ("firefox"):
                return new FirefoxDriver(getOptions());
            case ("msedge"):
                return new EdgeDriver(getOptions());
            case ("opera"):
                return new OperaDriver(getOptions());
            default:
                return new FirefoxDriver(getOptions());
        }
    }

    protected abstract Capabilities getOptions();
}
