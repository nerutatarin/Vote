package utils;

public class Utils {
    public static final String UTF8_BOM = "\uFEFF";

    public static String removeUTF8BOM(String str) {
        if (str == null) return null;

        if (str.startsWith(UTF8_BOM)) {
            str = str.substring(1);
        }
        return str;
    }
}
