package utils;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;
import static utils.Thesaurus.DirectoriesName.DEFAULT_BASE_LOG_PATH;
import static utils.Utils.createDirectoryIfNoExistInWorkDir;

public class WriteToLog {
    private final Logger log = Logger.getLogger(WriteToLog.class);
    private final String dirName;
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

    public void ipCountryCount(String ip, String country, int count) {
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
        File dir = createDir();
        File logFile = createLogFile(dir);

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(messageString.toString());
        } catch (IOException e) {
            error(e.getMessage());
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }

    @NotNull
    private File createDir() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM");
        return createDirectoryIfNoExistInWorkDir(DEFAULT_BASE_LOG_PATH + simpleDateFormat.format(new Date()) + "/" + dirName + "/");
    }

    private File createLogFile(File directory) {
        return new File(directory.getPath() + "/" + fileName + ".log");
    }

    private String getTimeStamp() {
        return new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
    }
}
