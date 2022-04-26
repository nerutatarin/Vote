package vote.driver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.System.setProperty;
import static java.nio.file.Paths.get;
import static utils.OSValidator.isUnix;
import static utils.OSValidator.isWindows;

public class Driver {
    private final String webDriverKey;
    private final String webDriverValue;

    public Driver(String webDriverKey, String webDriverValue) {
        this.webDriverKey = webDriverKey;
        this.webDriverValue = webDriverValue;
    }

    public void setPropertyDependsOnOS() {
        if (isWindows()) {
            setProperty(webDriverKey, getDriverPath(webDriverValue + ".exe"));
        }
        if (isUnix()) {
            setProperty(webDriverKey, getDriverPath(webDriverValue));
        }
    }

    private String getDriverPath(String nameDriver) {
        URL res = Driver.class.getClassLoader().getResource(nameDriver);
        File file = null;
        try {
            assert res != null;
            file = get(res.toURI()).toFile();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Не найден драйвер " + nameDriver + ": " + e);
        }
        return file.getAbsolutePath();
    }
}
