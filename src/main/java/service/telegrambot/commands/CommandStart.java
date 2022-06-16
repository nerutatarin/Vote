package service.telegrambot.commands;

import org.apache.log4j.Logger;

public class CommandStart extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return new StringBuilder()
                .append("/status - Статус сервера")
                .append("\n")
                .append("/participants - Список участников")
                .append("\n")
                .append("/resultsvote - Результаты голосования");
    }
}
