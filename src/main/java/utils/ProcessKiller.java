package utils;

import vote.vote2022.browsers.model.BrowserProcess;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static java.lang.Runtime.getRuntime;
import static java.lang.System.out;
import static utils.OSValidator.isUnix;
import static utils.OSValidator.isWindows;

public class ProcessKiller {
    private static final String TASKLIST = "tasklist";
    private static final String WIN_KILL_IM = "taskkill /IM ";
    private static final String WIN_KILL_PID = "taskkill /PID ";
    private static final String UNIX_KILL = "killall ";

    public void killer(BrowserProcess process) {
        String processName = process.getProcessName();
        boolean isRunning = isProcessRunning(processName);
        out.println("is " + processName + " running : " + isRunning);
        if (isRunning) {
            killProcess(processName);
        } else {
            out.println("Not able to find the process : " + process);
        }
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
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private void killProcess(String processName) {
        if (isWindows()) {
            kill(WIN_KILL_IM, processName);
        } else if (isUnix()) {
            kill(UNIX_KILL, processName);
        }
    }

    private void kill(String commandKill, String processName) {
        try {
            getRuntime().exec(commandKill + processName);
            out.println(processName + " killed successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
