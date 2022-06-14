package service.telegrambot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.telegrambot.commands.Commands;
import service.telegrambot.configurations.TelegramProperties;

public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger(TelegramBot.class);

    private final TelegramProperties config;

    public TelegramBot() {
        this.config = getConfig();
    }

    @Override
    public String getBotUsername() {
        return config.getName();
    }

    @Override
    public String getBotToken() {
        return config.getToken();
    }

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String text = update.getMessage().getText();

            SendMessage message = new SendMessage();
            Commands commands = CommandsFactory.getInstance(text);
            commands.execute(message, update);

            try {
                Integer messageId = update.getMessage().getMessageId();
                message.setReplyToMessageId(messageId); //Нужно ли отвечать на сообщение?
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            log.info("OutputData: " + "userId=" + message.getChatId() + " text=" + message.getText() + " messageId=" + message.getReplyToMessageId());
        }
    }

    private TelegramProperties getConfig() {
        return new TelegramProperties().parse();
    }
}
