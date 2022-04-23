package vote.vote2022.browsers;

import org.openqa.selenium.Proxy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import vote.vote2022.driver.Driver;
import vote.vote2022.browsers.model.BrowserProcess;

import static java.lang.System.out;
import static org.openqa.selenium.PageLoadStrategy.NORMAL;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_KEY;
import static utils.Thesaurus.Drivers.CHROME_DRIVER_VALUE;
import static utils.Thesaurus.ProxySettings.PROXY_IP_ADDRESS;
import static utils.Thesaurus.ProxySettings.PROXY_PORT;

public class ChromeBrowser extends Browsers {

    @Override
    protected void setDriverProperty() {
        out.println("Init Chrome Drivers...");
        Driver driver = new Driver(CHROME_DRIVER_KEY, CHROME_DRIVER_VALUE);
        driver.setPropertyDependsOnOS();
    }

    public BrowserProcess getProcess() {
        BrowserProcess process = new BrowserProcess();
        /*process.setProcessId(getCapabilities().getCapability(MOZ_PROCESS_ID).toString());*/
        process.setProcessName(getCapabilities().getBrowserName());
        return process;
    }

    @Override
    protected WebDriver getDriverInstance() {
        return new ChromeDriver(getOptions());
    }

    private ChromeOptions getOptions() {
        out.println("Chrome options...");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--proxy-server=socks5://" + PROXY_IP_ADDRESS + ":" + PROXY_PORT);
        chromeOptions.addArguments("enable-automation");
        chromeOptions.addArguments("incognito");
        //chromeOptions.addArguments("--headless");
        chromeOptions.addArguments("--disable-extensions");
        chromeOptions.addArguments("--dns-prefetch-disable");
        chromeOptions.addArguments("--disable-gpu");

        /*Proxy proxy = new Proxy();
        proxy.setSocksProxy(PROXY_IP_ADDRESS);
        chromeOptions.setProxy(proxy);*/

        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.setHeadless(false);
        chromeOptions.setPageLoadStrategy(NORMAL);
        return chromeOptions;
    }
}
