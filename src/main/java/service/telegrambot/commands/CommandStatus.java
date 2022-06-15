package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandStatus extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private final String STATUS_OK = "OK";

    @Override
    public SendMessage execute(Long userId, String text) {
        stringMessage();

        log.debug(getClass().getSimpleName() + ": " + stringMessage);

        return sendMessageBuild(userId);
    }

    public void stringMessage() {
        stringMessage.append(STATUS_OK);
    }
}
