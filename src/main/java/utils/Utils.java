package utils;

public class Utils {
    public static final String UTF8_BOM = "\uFEFF";

    public static String removeUTF8BOM(String str) {
        if (str == null || str.isEmpty()) return null;

        if (str.startsWith(UTF8_BOM)) {
            str = str.substring(1);
        }

        return str;
    }

    public static String substringBeforeSpace(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.replaceAll("\\S+$", "").trim();
    }

    public static String substringAfterSpace(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.replaceAll("^\\S+\\s", "");
    }
}
