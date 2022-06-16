package service.telegrambot.commands;

import utils.Utils;

import java.util.HashMap;
import java.util.Map;

import static service.telegrambot.commands.CommandsEnum.*;

public class CommandsFactory {
    private static final Map<String, Commands> filterFactory = new HashMap<>();

    static {
        filterFactory.put(COMMAND_START.getValue(), new CommandStart());
        filterFactory.put(COMMAND_STATUS.getValue(), new CommandStatus());
        filterFactory.put(COMMAND_PARTICIPANTS.getValue(), new CommandParticipants());
        filterFactory.put(COMMAND_RESULTS_VOTE.getValue(), new CommandResultsVote());
        filterFactory.put(COMMAND_DEFAULT.getValue(), new CommandDefault());
    }

    public static Commands getInstance(String data) {
        return filterFactory.get(getCommand(data));
    }

    private static String getCommand(String data) {
        return Utils.substringBeforeSpace(data);
    }
}
