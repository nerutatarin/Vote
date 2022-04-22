package vote.vote2022.driver;

import utils.OSValidator;
import vote.vote2022.exceptions.DriverNotFoundException;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.System.out;
import static java.lang.System.setProperty;
import static java.nio.file.Paths.get;
import static utils.OSValidator.isUnix;
import static utils.OSValidator.isWindows;

public class Driver {

    public void initGecko() {
        out.println("Init Firefox drivers...");
        setPropertyDependsOnOS();
    }

    private void setPropertyDependsOnOS() {
        if (isWindows()) {
            setProperty("webdriver.gecko.driver", getDriverPath("geckodriver_0.31.0.exe"));
        }
        if (isUnix()) {
            setProperty("webdriver.gecko.driver", getDriverPath("geckodriver_0.31.0"));
        }
    }

    private String getDriverPath(String nameDriver) {
        URL res = getClass().getClassLoader().getResource(nameDriver);
        File file = null;
        try {
            assert res != null;
            file = get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Не найден драйвер: " + e);
        }
        return file.getAbsolutePath();
    }
}
