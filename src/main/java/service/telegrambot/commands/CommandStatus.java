package service.telegrambot.commands;

import org.apache.log4j.Logger;

public class CommandStatus extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private final String STATUS_OK = "OK";

    @Override
    protected StringBuilder replyMessageMake() {
        return new StringBuilder().append(STATUS_OK);
    }
}
