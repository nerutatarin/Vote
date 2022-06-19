package service.telegrambot.handlers;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RoutingUpdate {
    public SendMessage routing(Update update) {
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            CallBackHandler callBackHandler = new CallBackHandler();
            return callBackHandler.callBackHandler(callbackQuery);
        } else {
            Message message = update.getMessage();
            if (message != null) {
                MessageHandler messageHandler = new MessageHandler();
                return messageHandler.messageHandler(message);
            }
        }
        return null;
    }
}
