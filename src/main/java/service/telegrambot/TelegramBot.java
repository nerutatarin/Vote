package service.telegrambot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.telegrambot.configurations.TelegramProperties;
import service.telegrambot.handlers.RoutingUpdate;

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

    public void onUpdateReceived(Update update) {
        RoutingUpdate routingUpdate = new RoutingUpdate();
        SendMessage sendMessage = routingUpdate.routing(update);
        executeMessage(sendMessage);
    }

    private <T extends BotApiMethod> void executeMessage(T message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private TelegramProperties getConfig() {
        return new TelegramProperties().parse();
    }
}
