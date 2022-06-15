package utils;

import org.jetbrains.annotations.NotNull;

public class Utils {
    public static final String UTF8_BOM = "\uFEFF";

    /**
     * Возвращаем строку без UTF8_BOM
     *
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
     * Преобразует строку в int, в случае ошибки возращает 0.
     *
     * @param str входная строка
     * @return число преобразованное из строки или 0 в случае ошибки
     */
    public static int parseInt(String str) {
        return parseInt(str, 0);
    }

    /**
     * Преобразует строку в int, в случае ошибки возращает defaultValue.
     *
     * @param str          входная строка
     * @param defaultValue значение возращаемое в случае ошибки при преобразовании
     * @return число преобразованное из строки или defaultValue в случае ошибки
     */
    public static int parseInt(String str, int defaultValue) {
        int value = defaultValue;
        try {
            value = Integer.parseInt(str);
        } catch (Exception ignore) {
        }
        return value;
    }

    /**
     * Возвращает подстроку до первого пробела
     *
     * @param str входная строка
     * @return первая подстрока или str
     */
    public static String substringBeforeSpace(String str) {
        if (str == null || str.isEmpty()) return str;

        String[] substrings = getSubstrings(str);
        return substrings.length != 0 ? substrings[0] : str;
    }

    /**
     * Возвращает 1-ю подстроку после пробела
     *
     * @param str
     * @return
     */
    public static String firstSubstringAfterSpace(String str) {
        if (str == null || str.isEmpty()) return str;

        String[] substrings = getSubstrings(str);
        return substrings.length >= 2 ? substrings[1] : str;
    }

    @NotNull
    private static String[] getSubstrings(String str) {
        return str.split(" ");
    }

    /**
     * Возвращает подстроку до первого пробела, используя регулярные выражения
     *
     * @param str
     * @return
     */
    public static String substringBeforeSpaceByRegex(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.replaceAll("\\S+$", "").trim();
    }

    /**
     * Возвращает подстроку после первого пробела, используя регулярные выражения
     *
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
