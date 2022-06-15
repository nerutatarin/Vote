package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandStart extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    public SendMessage execute(Long userId, String text) {
        stringMessage();

        log.debug(getClass().getSimpleName() + ": " + stringMessage);

        return sendMessageBuild(userId);
    }

    public void stringMessage() {
        stringMessage
                .append("/status - Статус сервера")
                .append("\n")
                .append("/participants - Список участников")
                .append("\n")
                .append("/resultsvote - Результаты голосования");
    }
}
