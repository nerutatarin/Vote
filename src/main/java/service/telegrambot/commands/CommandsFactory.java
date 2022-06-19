package service.telegrambot.commands;

import java.util.HashMap;
import java.util.Map;

import static service.telegrambot.commands.CommandsEnum.*;
import static utils.Utils.substringBeforeSpace;

public class CommandsFactory {
    private static final Map<String, Commands> filterFactory = new HashMap<>();

    static {
        filterFactory.put(COMMAND_START.getValue(), new CommandStart());
        filterFactory.put(COMMAND_STATUS.getValue(), new CommandStatus());
        filterFactory.put(COMMAND_MEMBERS.getValue(), new CommandMembers());
        filterFactory.put(COMMAND_RESULT.getValue(), new CommandResult());
        filterFactory.put(COMMAND_VOTE.getValue(), new CommandVote());
        filterFactory.put(COMMAND_NOMINATIONS.getValue(), new CommandNominations());
        filterFactory.put(COMMAND_DEFAULT.getValue(), new CommandDefault());
    }

    public static Commands getInstance(String data) {
        return filterFactory.get(getCommand(data));
    }

    private static String getCommand(String data) {
        return substringBeforeSpace(data);
    }
}
