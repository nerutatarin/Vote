package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public interface Commands {

    SendMessage execute(String chatId, String data);
}
