package service.telegrambot.handlers;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import service.telegrambot.commands.Commands;
import service.telegrambot.commands.CommandsFactory;

import static org.apache.log4j.Logger.getLogger;

public class CallBackHandler {
    private static final Logger log = getLogger(CallBackHandler.class);

    public SendMessage callBackHandler(CallbackQuery buttonQuery) {
        final String chatId = buttonQuery.getMessage().getChatId().toString();
        String data = buttonQuery.getData();
        log.debug("InputData: " + "chatId=" + chatId + " data=" + data);


        Commands commands = CommandsFactory.getInstance(data);
        SendMessage sendMessage = commands.execute(chatId, data);
        log.debug("OutputData: " + "chatId=" + sendMessage.getChatId() + " data=" + sendMessage.getText());
        return sendMessage;
    }
}
