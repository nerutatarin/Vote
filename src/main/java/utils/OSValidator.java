package utils;

import static java.lang.System.getProperty;

public class OSValidator {

    public static String getOsName() {
        return getProperty("os.name").toLowerCase();
    }

    public static boolean isWindows(){
        return getOsName().contains("win");
    }

    public static boolean isUnix() {
        return getOsName().contains("nix") || getOsName().contains("nux") || getOsName().indexOf("aix") > 0;
    }

    public static boolean isMac(String mac) {
        return getOsName().contains(mac);
    }

}
