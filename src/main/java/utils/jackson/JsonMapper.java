package utils.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static utils.Thesaurus.JSON_PATH;

public class JsonMapper {


    /**
     * Десериализуем файл в объект
     *
     * @param fileName
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T fileToObject(String fileName, Class<T> clazz) {
        final ObjectMapper mapper = newMapper();
        try (Reader reader = new FileReader(JSON_PATH + fileName)) {
            return mapper.readValue(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Десериализуем файл в лист объектов
     *
     * @param fileName
     * @param type
     * @param <T>
     * @return
     */
    public static <T> List<T> fileToListObject(String fileName, Class<T> type) {
        return fileToListObject(fileName, ArrayList.class, type);
    }

    /**
     * Десериализуем файл в лист объектов
     *
     * @param fileName
     * @param type
     * @param elementType
     * @param <T>
     * @return
     */
    public static <T> List<T> fileToListObject(String fileName, Class<? extends Collection> type, Class<T> elementType) {
        final ObjectMapper mapper = newMapper();
        try (Reader reader = new FileReader(JSON_PATH + fileName)) {
            return mapper.readValue(reader, mapper.getTypeFactory().constructCollectionType(type, elementType));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static ObjectMapper newMapper() {
        final ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(new Jdk8Module());
        return mapper;
    }

    /**
     * Сериализуем объект в файл
     *
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectToFile(T object, String fileName) {
        if (object == null) return;
        ObjectMapper objectMapper = new ObjectMapper();
        try (FileWriter file = new FileWriter(JSON_PATH + fileName)) {
            objectMapper.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сериализуем объект в файл. Красивый режим
     *
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectToFilePretty(T object, String fileName) {
        if (object == null) return;
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try (FileWriter file = new FileWriter(JSON_PATH + fileName)) {
            objectWriter.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Сериализуем лист объектов в файл. Красивый режим
     *
     * @param object
     * @param fileName
     * @param <T>
     */
    public static <T> void objectListToFilePretty(List<T> object, String fileName) {
        if (object == null || object.size() == 0) return;
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try (FileWriter file = new FileWriter(JSON_PATH + fileName)) {
            objectWriter.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
