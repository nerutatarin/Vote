package utils;

import org.apache.log4j.Logger;
import utils.ipaddress.model.MyIpAddress;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.apache.log4j.Logger.getLogger;
import static utils.Thesaurus.DateTimePatterns.PATTERN_DDMMYYYYHHMMSS;

public class WriteToLog {
    private static final Logger log = getLogger(WriteToLog.class);

    public static void writeToLog(String browserName, MyIpAddress myIpAddress, String title, String count) {
        String timeStamp = new SimpleDateFormat(PATTERN_DDMMYYYYHHMMSS).format(new Date());

        String path = "src/resources/logs/" + browserName + "/";
        String fileName = Utils.removeUTF8BOM(title) + ".log";

        File directory = new File(path);
        if (!directory.exists()) {
            directory.mkdir();
        }

        File logFile = new File(directory + "/" + fileName);

        String ip = myIpAddress.getIp();
        String country = myIpAddress.getCountry();

        try (PrintWriter writer = new PrintWriter(new FileWriter(logFile, true))) {
            writer.write(timeStamp + " ip: " + ip + " country: " + country + " count: " + count + "\n");
        } catch (IOException e) {
            log.debug("Ошибка операции ввода-вывода: " + e);
        }
    }
}
