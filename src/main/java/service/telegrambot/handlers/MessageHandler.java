package service.telegrambot.handlers;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import service.telegrambot.commands.Commands;
import service.telegrambot.keyboards.InlineKeyboardMaker;

import static org.apache.log4j.Logger.getLogger;
import static service.telegrambot.commands.CommandsEnum.COMMAND_START;
import static service.telegrambot.commands.CommandsFactory.getInstance;

public class MessageHandler {
    private static final Logger log = getLogger(MessageHandler.class);

    public SendMessage messageHandler(Message message) {
        final String chatId = message.getChatId().toString();
        String data = message.getText();
        log.debug("InputData: " + "chatId=" + chatId + " data=" + data);

        Commands commands = getInstance(data);
        SendMessage sendMessage = commands.execute(chatId, data);

        if (data.equals(COMMAND_START.getValue())) {
            InlineKeyboardMaker inlineKeyboardMaker = new InlineKeyboardMaker();
            sendMessage.setReplyMarkup(inlineKeyboardMaker.createInlineKeyboard());
        }

        log.debug("OutputData: " + "chatId=" + sendMessage.getChatId() + " data=" + sendMessage.getText());
        return sendMessage;
    }
}
