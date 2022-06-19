package service.telegrambot.commands;

import org.apache.log4j.Logger;

public class CommandStart extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return new StringBuilder()
                .append("/status - Статус сервера")
                .append("\n")
                .append("/vote - Проголосовать(дефолт)")
                .append("\n")
                .append("/members - Список участников")
                .append("\n")
                .append("/nominations - Список номинаций")
                .append("\n")
                .append("/result - Результат голосования. Принимает аргумент id участника. Без аргумента возвращает результат по участникам из конфига");
    }
}
