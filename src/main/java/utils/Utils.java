package utils;

public class Utils {
    public static final String UTF8_BOM = "\uFEFF";

    /**
     * Возвращаем строку без UTF8_BOM
     * @param str
     * @return
     */
    public static String removeUTF8BOM(String str) {
        if (str == null || str.isEmpty()) return null;

        if (str.startsWith(UTF8_BOM)) {
            str = str.substring(1);
        }

        return str;
    }

    /**
     * Возвращает подстроку до первого пробела
     * @param str
     * @return
     */
    public static String substringBeforeSpace(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.split(" ")[0];
    }

    /**
     * Возвращает подстроку до первого пробела, используя регулярные выражения
     * @param str
     * @return
     */
    public static String substringBeforeSpaceByRegex(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.replaceAll("\\S+$", "").trim();
    }

    /**
     * Возвращает подстроку после первого пробела, используя регулярные выражения
     * @param str
     * @return
     */
    public static String substringAfterSpaceByRegex(String str) {
        if (str == null || str.isEmpty()) return str;

       // return str.replaceAll("\\s+([^\\n]+)", "").trim();
        return str.replaceAll("^\\S+\\s", "");
    }

    public static boolean isBlankString(String value) {
        return value == null || value.trim().length() == 0;
    }
}
