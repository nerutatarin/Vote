package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class CommandDefault implements Commands {
    @Override
    public void execute(SendMessage message, Update update) {
        Long userId = update.getMessage().getChatId();
        message.setChatId(String.valueOf(userId));
    }
}
