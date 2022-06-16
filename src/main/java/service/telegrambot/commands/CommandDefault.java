package service.telegrambot.commands;

import org.apache.log4j.Logger;

public class CommandDefault extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return new StringBuilder();
    }
}
