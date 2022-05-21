package utils;

import org.apache.log4j.Logger;
import vote.pagemanager.PageManager;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;

public class WriteToLog {
    private static final Logger log = getLogger(WriteToLog.class);

    public static void writeToLog(PageManager pageManager) {
        if (pageManager == null) return;

        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
        String logFile = "src/resources/logs/" + "log.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(timeStamp + " ip: " + pageManager.getIpAddress("https://api.ipify.org/") + "\n");
        } catch (IOException e) {
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }
}
