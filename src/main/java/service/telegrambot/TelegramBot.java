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
            Long userId = update.getMessage().getChatId();
            String text = update.getMessage().getText();
            Integer messageId = update.getMessage().getMessageId();

            log.debug("InputData: " + "userId=" + userId + " text=" + text + " messageId=" + messageId);

            Commands commands = CommandsFactory.getInstance(text);
            SendMessage sendMessage = commands.execute(userId, text);

            try {
                sendMessage.setReplyToMessageId(messageId); //Нужно ли отвечать на сообщение?

                execute(sendMessage);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }

            log.debug("OutputData: " + "userId=" + sendMessage.getChatId() + " text=" + sendMessage.getText() + " messageId=" + sendMessage.getReplyToMessageId());
        }
    }

    private TelegramProperties getConfig() {
        return new TelegramProperties().parse();
    }
}
