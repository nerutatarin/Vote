package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandDefault extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private StringBuilder stringBuilder = new StringBuilder();
    @Override
    public SendMessage execute(Long userId, String text) {
        String stringMessage = stringMessage();

        return sendMessageBuild(userId, stringMessage);
    }

    public String stringMessage() {
        return "";
    }
}
