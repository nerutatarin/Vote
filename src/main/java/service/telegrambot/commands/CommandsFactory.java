package service.telegrambot.commands;

import utils.Utils;

import java.util.HashMap;
import java.util.Map;

public class CommandsFactory {

    private static final String COMMAND_START = "/start";
    private static final String COMMAND_STATUS = "/status";
    private static final String COMMAND_PARTICIPANTS = "/participants";
    private static final String COMMAND_RESULTS_VOTE = "/resultsvote";
    private static final String COMMAND_DEFAULT = "";
    private static final Map<String, Commands> filterFactory = new HashMap<>();

    static {
        filterFactory.put(COMMAND_START, new CommandStart());
        filterFactory.put(COMMAND_STATUS, new CommandStatus());
        filterFactory.put(COMMAND_PARTICIPANTS, new CommandParticipants());
        filterFactory.put(COMMAND_RESULTS_VOTE, new CommandResultsVote());
        filterFactory.put(COMMAND_DEFAULT, new CommandDefault());
    }

    public static Commands getInstance(String text) {
        return filterFactory.get(getCommand(text));
    }

    private static String getCommand(String text) {
        return Utils.substringBeforeSpace(text);
    }
}
