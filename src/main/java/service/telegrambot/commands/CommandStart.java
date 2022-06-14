package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class CommandStart extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    public SendMessage execute(Long userId, String text) {
        String stringMessage = stringMessage();

        log.info(stringMessage);

        return sendMessageBuild(userId, stringMessage);
    }

    public String stringMessage() {
        return "/status - Статус сервера" + "\n" + "/participants - Список участников" + "\n" + "/resultsvote - Результаты голосования";
    }
}
