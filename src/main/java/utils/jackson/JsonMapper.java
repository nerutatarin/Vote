package utils.jackson;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.util.Collection;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.DirectoriesName.JSON_PATH;
import static utils.Utils.createDirectoryIfNoExistInWorkDir;

public class JsonMapper {
    private static final Logger log = getLogger(JsonMapper.class);

    /**
     * Десериализуем файл в объект
     *
     * @param fileName
     * @param clazz
     * @param <T>
     * @return Object
     */
    public static <T> T fileToObject(String fileName, Class<T> clazz){
        final ObjectMapper mapper = newMapper();
        try (Reader reader = new FileReader(JSON_PATH + fileName)) {
            return mapper.readValue(reader, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            //throw new VoteException("Ошибка десериализации файла " + PAGE_BEFORE_VOTING_JSON);
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
        return fileToListObject(fileName, List.class, type);
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
        // TODO: 04.07.2022 проверка каталогов
        log.info("Сохраняем объект " + object.getClass().getSimpleName() + " в " + fileName  + "...");
        createDirectoryIfNoExistInWorkDir(JSON_PATH);

        if (object == null) return;
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try (FileWriter file = new FileWriter(JSON_PATH + fileName)) {
            objectWriter.writeValue(file, object);
        } catch (IOException e) {
            log.info("Не удалось сохранить объект " + object.getClass().getSimpleName() + " в " + fileName  + "...");
            e.printStackTrace();
        }
    }

    /**
     * Сериализуем лист объектов в файл. Красивый режим
     *
     * @param object
     * @param fileName
     */
    public static void objectListToFilePretty(List<?> object, String fileName) {
        if (object == null || object.size() == 0) return;
        ObjectWriter objectWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();
        try (FileWriter file = new FileWriter(JSON_PATH + fileName)) {
            objectWriter.writeValue(file, object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
