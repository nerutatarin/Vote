package utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.apache.commons.io.IOUtils;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.List;

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

        return str.split(" ")[0];
    }

    /**
     * Возвращает подстроку до первого пробела
     * @param str
     * @return
     */
    public static String substringBeforeSpaceByRegex(String str) {
        if (str == null || str.isEmpty()) return str;

        return str.replaceAll("\\S+", "").trim();
    }

    /**
     * Возвращает подстроку после первого пробела
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

    public static <T> void objectToFileWithGson(T object, String fileName) {
        if (object == null) return;
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> void objectToFileWithGsonPretty(T object, String fileName) {
        if (object == null) return;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> List<T> fileToArrayObjectWithGson(String fileName, Class<T> clazz) {
        try (Reader reader = new FileReader(fileName)) {
            ListOfJson<T> typeOfT = new ListOfJson<>(clazz);
            new Gson().fromJson(reader, typeOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void objectListToFileWithGsonPretty(List<T> object, String fileName) {
        if (object == null || object.size() == 0) return;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static <T> T fileToObjectWithGson(String fileName, Class<T> clazz) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fileToObjectWithGsonExposeMode(String fileName, Class<T> clazz) {
        Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();

        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> T fileToListObjectWithGson(String fileName, Class<T> clazz) {
        Gson gson = new Gson();
        Type listType = new TypeToken<List<T>>() {}.getType();
        return gson.fromJson(loadFileFromClasspath(fileName), listType);
    }

    private static String loadFileFromClasspath(String fileName) {
        ClassLoader classLoader = Utils.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            return IOUtils.toString(inputStream, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }
}
