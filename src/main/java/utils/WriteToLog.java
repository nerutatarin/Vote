package utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;

public class WriteToLog {
    private final Logger log = Logger.getLogger(WriteToLog.class);
    private final String dirName;

    private final String pathToLog = "src/resources/logs/";
    private String fileName;
    private StringBuilder messageString = new StringBuilder();

    public WriteToLog(String dirName) {
        this.dirName = dirName;
    }

    public WriteToLog(String dirName, String fileName) {
        this.dirName = dirName;
        this.fileName = Utils.removeUTF8BOM(fileName);
    }

    public void error(String message) {
        this.fileName = "error";

        getMessageString()
                .append(" ")
                .append(message)
                .append("\n");
        write(messageString);
    }

    public void ipCountryCount(String ip, String country, String count) {
        getMessageString()
                .append(" ip: ").append(ip)
                .append(" country: ").append(country)
                .append(" count: ").append(count)
                .append("\n");

        write(messageString);
    }

    private StringBuilder getMessageString() {
        return messageString.append(getTimeStamp());
    }

    private void write(StringBuilder messageString) {
        File directory = verifyDirectory();
        File logFile = createLogFile(directory);

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(messageString.toString());
        } catch (IOException e) {
            error(e.getMessage());
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }

    private File verifyDirectory() {
        File directory = new File(pathToLog + dirName + "/");

        if (directory.exists()) return directory;

        directory.mkdirs();
        return directory;
    }

    private File createLogFile(File directory) {
        return new File(directory.getPath() + "/" + fileName + ".log");
    }

    private String getTimeStamp() {
        return new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
    }
}
