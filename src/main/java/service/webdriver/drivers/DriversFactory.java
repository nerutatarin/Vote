package service.webdriver.drivers;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.opera.OperaDriver;
import service.webdriver.BrowserImpl;

import static utils.Thesaurus.Drivers.*;

public abstract class DriversFactory extends BrowserImpl {

    // TODO: 04.07.2022 переработать фабрику, пиздец говнище
    public WebDriver getBrowsersFactory(String browserName) {
        switch (browserName) {
            case (CHROME):
                return new ChromeDriver(capabilities());
            case (CHROMIUM):
                return new ChromeDriver(capabilities());
            case (FIREFOX):
                return new FirefoxDriver(capabilities());
            case (MSEDGE):
                return new EdgeDriver(capabilities());
            case (OPERA):
                return new OperaDriver(capabilities());
            default:
                return new FirefoxDriver(capabilities());
        }
    }

    private Capabilities capabilities() {
        return getOptions();
    }
}
