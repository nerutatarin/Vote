package service.telegrambot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import service.telegrambot.configurations.TelegramConfig;
import service.telegrambot.handlers.Handler;
import service.telegrambot.handlers.RouterFactory;
import utils.yaml.YamlParser;

import static utils.Thesaurus.FilesNameYaml.TELEGRAM_CONFIG_YAML;

public class TelegramBot extends TelegramLongPollingBot {
    private static final Logger log = Logger.getLogger(TelegramBot.class);

    private final TelegramConfig config;

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
        Handler handler = RouterFactory.getInstance(update);
        SendMessage sendMessage = handler.getMessage(update);
        executeMessage(sendMessage);
    }

    private <T extends BotApiMethod<Message>> void executeMessage(T message) {
        try {
            execute(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private TelegramConfig getConfig() {
        return YamlParser.parse(TelegramConfig.class, TELEGRAM_CONFIG_YAML);
    }
}
