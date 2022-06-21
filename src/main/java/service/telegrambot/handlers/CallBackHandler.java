package service.telegrambot.handlers;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.telegrambot.commands.Commands;

import static org.apache.log4j.Logger.getLogger;
import static service.telegrambot.commands.CommandsFactory.getInstance;

class CallBackHandler implements Handler {
    private static final Logger log = getLogger(CallBackHandler.class);

    @Override
    public SendMessage getMessage(Update update) {
        CallbackQuery callbackQuery = update.getCallbackQuery();

        final String chatId = callbackQuery.getMessage().getChatId().toString();
        String data = callbackQuery.getData();
        log.debug("InputData: " + "chatId=" + chatId + " data=" + data);

        Commands commands = getInstance(data);
        SendMessage sendMessage = commands.execute(chatId, data);
        log.debug("OutputData: " + "chatId=" + sendMessage.getChatId() + " data=" + sendMessage.getText());
        return sendMessage;
    }
}
