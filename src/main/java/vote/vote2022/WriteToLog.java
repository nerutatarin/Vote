package vote.vote2022;

import org.apache.log4j.Logger;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;

public class WriteToLog {
    private static final Logger log = getLogger(WriteToLog.class);

    public static void writeToLog(String ipAddress) {
        if (ipAddress == null) return;
        log.info("ipAddress: " + ipAddress);

        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());
        String logFile = "src/resources/logs/" + "log.log";
        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(timeStamp + " ip: " + ipAddress + "\n");
        } catch (IOException e) {
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }
}
