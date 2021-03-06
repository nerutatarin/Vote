package utils;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

import static java.lang.Runtime.getRuntime;
import static java.util.Arrays.asList;
import static org.apache.log4j.Logger.getLogger;
import static utils.OSValidator.isUnix;
import static utils.OSValidator.isWindows;
import static utils.Thesaurus.Drivers.DRIVERS_MAP;

public class ProcessKiller {
    private static final Logger log = getLogger(ProcessKiller.class);

    private static final String TASKLIST = "tasklist";
    private static final String WIN_KILL_IM = "taskkill /F /IM ";
    private static final String WIN_KILL_PID = "taskkill /PID ";
    private static final String UNIX_KILL = "killall -r ";

    public void killer(String processName) {
        if (isWindows()) {
            boolean isRunning = isProcessRunning(processName);
            if (isRunning) {
                killProcess(processName);
            } else {
                log.info(processName + " Процесс не найден");
            }
        }
        if (isUnix()) {
            killProcess(processName);
        }
    }

    public void killer(List<String> processNames) {
        processNames.forEach(this::killer);
    }

    private boolean isProcessRunning(String processName) {
        try {
            Process processList = getRuntime().exec(TASKLIST);
            BufferedReader reader = new BufferedReader(new InputStreamReader(processList.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.startsWith(processName)) {
                    return true;
                }
            }
        } catch (Exception e) {
            log.debug(processName + " При поиске процесса произошла ошибка: " + e.getMessage());
        }
        return false;
    }

    private void killProcess(String processName) {
        if (isWindows()) {
            kill(WIN_KILL_IM, processName + ".exe");
        } else if (isUnix()) {
            kill(UNIX_KILL, processName);
        }
    }

    private void kill(String commandKill, String processName) {
        try {
            getRuntime().exec(commandKill + processName);
            log.info(processName + " Успешное завершение работы процесса");
        } catch (Exception e) {
            log.debug(processName + " Попытка прикончить процесс завершилась с ошибкой: " + e.getMessage());
        }
    }

    public void killAllRunningProcesses(String browserName) {
        DRIVERS_MAP.entrySet()
                .stream()
                .filter(entry -> entry.getKey().equals(browserName))
                .forEach(entry -> killer(asList(entry.getKey(), entry.getValue())));
    }
}
