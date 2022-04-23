package vote.vote2022.driver;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;

import static java.lang.System.out;
import static java.lang.System.setProperty;
import static java.nio.file.Paths.get;
import static utils.OSValidator.isUnix;
import static utils.OSValidator.isWindows;
import static utils.Thesaurus.Drivers.GECKO_DRIVER_NAME;
import static utils.Thesaurus.Drivers.WEBDRIVER_GECKO_DRIVER;

public class Driver {

    public void initGecko() {
        out.println("Init Firefox drivers...");
        setPropertyDependsOnOS();
    }

    private void setPropertyDependsOnOS() {

        if (isWindows()) {
            setProperty(WEBDRIVER_GECKO_DRIVER, getDriverPath(GECKO_DRIVER_NAME + ".exe"));
        }
        if (isUnix()) {
            setProperty(WEBDRIVER_GECKO_DRIVER, getDriverPath(GECKO_DRIVER_NAME));
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
