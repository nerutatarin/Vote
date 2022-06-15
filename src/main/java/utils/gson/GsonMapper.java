package utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import utils.ListOfJson;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GsonMapper {

    /**
     * Десериализуем файл в лист объектов
     * @param fileName
     * @param type
     * @return
     * @param <T>
     */
    public static <T> List<T> fileToListObject(String fileName, Class<T> type) {
        return fileToListObject(fileName, ArrayList.class, type);
    }

    /**
     * Десериализуем файл в лист объектов
     * @param fileName
     * @param type
     * @param elementType
     * @return
     * @param <T>
     */
    public static <T> List<T> fileToListObject(String fileName, Class<? extends Collection> type, Class<T> elementType) {
        try (Reader reader = new FileReader(fileName)){
            Type typeToken = TypeToken.getParameterized(type, elementType).getType();
            return new Gson().fromJson(reader, typeToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Десериализуем файл в лист объектов
     * @param fileName
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> List<T> fileToArrayObject(String fileName, Class<T> clazz) {
        try (Reader reader = new FileReader(fileName)) {
            ListOfJson<T> typeOfT = new ListOfJson<>(clazz);
            new Gson().fromJson(reader, typeOfT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Десериализуем файл в объект
     * @param fileName
     * @param clazz
     * @return
     * @param <T>
     */
    public static <T> T fileToObject(String fileName, Class<T> clazz) {
        Gson gson = new Gson();
        try (Reader reader = new FileReader(fileName)) {
            return gson.fromJson(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Сериализуем объект в файл
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectToFile(T object, String fileName) {
        if (object == null) return;
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сериализуем объект в файл. Красивый режим
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectToFilePretty(T object, String fileName) {
        if (object == null) return;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сериализуем лист объектов в файл. Красивый режим
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectsListToFilePretty(List<T> object, String fileName) {
        if (object == null || object.size() == 0) return;
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        try (FileWriter writer = new FileWriter(fileName)) {
            gson.toJson(object, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
