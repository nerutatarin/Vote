package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandStart extends CommandsImpl {
    @Override
    public void execute(SendMessage message, Update update) {
        Long userId = update.getMessage().getChatId();

        stringBuild();

        log.info(builder.toString());

        messageBuild(message, userId);
    }

    protected void stringBuild() {
        builder.append("/status - Статус сервера")
                .append("\n")
                .append("/participants - Список участников")
                .append("\n")
                .append("/resultsvote - Результаты голосования");
    }
}
