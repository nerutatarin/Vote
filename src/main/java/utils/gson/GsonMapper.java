package utils.gson;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GsonMapper {

    public static <T> List<T> fileToObject(String fileName, Class<T> type) {
        return fileToObject(fileName, ArrayList.class, type);
    }

    public static <T> List<T> fileToObject(String fileName, Class<? extends Collection> type, Class<T> elementType) {
        try (Reader reader = new FileReader(fileName)){
            Type typeToken = TypeToken.getParameterized(type, elementType).getType();
            return new Gson().fromJson(reader, typeToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
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
}
