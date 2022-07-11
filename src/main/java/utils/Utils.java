package utils;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import service.exception.VoteServiceException;

import java.io.File;
import java.util.*;

import static org.apache.log4j.Logger.getLogger;

public class Utils {
    private static final Logger log = getLogger(Utils.class);

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
     * Проверяет, является ли строка пустой, состоящей из пробельных символов или null.
     *
     * @param value проверяемая строка
     * @return false - если пустая, состоит из пробельных символов или null
     */
    public static boolean notBlankString(String value) {
        return !isBlankString(value);
    }

    /**
     * Проверяет, является ли строка пустой или null.
     * @param value проверяемая строка
     * @return true - если пустая или null
     */
    public static boolean isEmptyString( String value )
    {
        return value == null || value.isEmpty();
    }

    /**
     * Проверяет, является ли строка пустой или null.
     * @param value проверяемая строка
     * @return false если пустая или null
     */
    public static boolean notEmptyString( String value )
    {
        return ! isEmptyString(value);
    }

    /**
     * Преобразует строку с разделителями - запятыми или точками с запятой к списку Integer.
     *
     * @param valuesStr
     * @return
     */
    public static List<Integer> toIntegerList(String valuesStr) {
        return toIntegerList(valuesStr, ",;");
    }

    /**
     * Преобразует строку с произвольными разделителями - символами в delims в список Integer.
     *
     * @param valuesStr
     * @param delims
     * @return
     */
    public static List<Integer> toIntegerList(String valuesStr, String delims) {
        List<Integer> result = new ArrayList<>();

        if (notBlankString(valuesStr)) {
            StringTokenizer st = new StringTokenizer(valuesStr.trim(), delims);
            while (st.hasMoreTokens()) {
                try {
                    result.add(new Integer(st.nextToken().trim()));
                } catch (Exception ignore) {
                }
            }
        }

        return result;
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

    public static boolean nullOrEmpty(Map<?, ?> map) {
        return map == null || map.isEmpty();
    }

    public static String getUserAgent(WebDriver driver) {
        return (String) ((JavascriptExecutor) driver).executeScript("return navigator.userAgent;");
    }

    public static <T> T requireNonNull(T value, String errorMessage) {
        if (value == null) throw new VoteServiceException(errorMessage);
        return value;
    }

    public static <T extends Collection<?>> Collection<?> requireNonEmpty(List<?> value, String errorMessage) {
        if (value.isEmpty()) throw new VoteServiceException(errorMessage);
        return value;
    }

    public static void sleep(int millis) {
        try {
            log.info("Ожидаем " + millis/1000 + " сек...");
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            throw new RuntimeException("Во время паузы произошла ошибка: " + e.getMessage());
        }
    }
}
