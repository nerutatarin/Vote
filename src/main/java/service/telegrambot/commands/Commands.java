package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public interface Commands {
    void execute(SendMessage message, Update update);
}
