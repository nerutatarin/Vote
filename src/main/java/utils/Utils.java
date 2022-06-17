package utils;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Utils {
    private static final String UTF8_BOM = "\uFEFF";
    private static final String DEFAULT_DELIM = ", ";

    /**
     * Возвращаем строку без UTF8_BOM
     *
     * @param str
     * @return
     */
    public static String removeUTF8BOM(String str) {
        if (isBlankString(str)) return str;

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
        if (isBlankString(str)) return str;

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
        if (isBlankString(str)) return str;

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
        if (isBlankString(str)) return str;

        return str.replaceAll("\\S+$", "").trim();
    }

    /**
     * Возвращает подстроку после первого пробела, используя регулярные выражения
     *
     * @param str
     * @return
     */
    public static String substringAfterSpaceByRegex(String str) {
        if (isBlankString(str)) return str;

        // return str.replaceAll("\\s+([^\\n]+)", "").trim();
        return str.replaceAll("^\\S+\\s", "");
    }

    /**
     * Проверяет, является ли строка пустой, состоящей из пробельных символов или null.
     *
     * @param value проверяемая строка
     * @return true если пустая, состоит из пробельных символов или null
     */
    public static boolean isBlankString(String value) {
        return value == null || value.trim().length() == 0;
    }

    /**
     * Вызывается {@link #toString(Collection, String, String)} с
     * параметром emptyValue="", delim={@link #DEFAULT_DELIM}.
     *
     * @param valuesList
     * @return
     */
    public static String toString(Collection<?> valuesList) {
        return toString(valuesList, "", DEFAULT_DELIM);
    }

    /**
     * Преобразовывает коллекцию в строку, разделенную значениями delim.
     *
     * @param valuesList коллектиция
     * @param emptyValue значение при пустом списке
     * @param delim      разделитель
     * @return
     */
    public static String toString(Collection<?> valuesList, String emptyValue, String delim) {
        if (valuesList != null && valuesList.size() != 0) {
            StringBuilder result = new StringBuilder();
            for (Object next : valuesList) {
                addObjectToList(result, next, delim);
            }
            return result.toString();
        }
        return emptyValue;
    }

    private static void addObjectToList(StringBuilder result, Object next, String delim) {
        if (result.length() != 0) result.append(delim);

        if (next instanceof Integer) {
            result.append(((Integer) next).intValue());
        } else {
            result.append(next);
        }
    }

    public static File createDirectoryIfNoExistInWorkDir(String dirName) {
        File dir = new File(dirName);
        if (!dir.exists()) {
            File logFile = new File("src");
            dir = new File(logFile.getAbsolutePath() + "/../" + dirName);
            dir.mkdirs();
        }
        return dir;
    }

    public static <T> List<T> concat(List<T> first, List<T> second) {
        ArrayList<T> result = new ArrayList<>(first);
        result.addAll(second);

        return result;
    }

    public static boolean nullOrEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }
}
