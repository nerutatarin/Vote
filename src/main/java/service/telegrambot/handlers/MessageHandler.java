package service.telegrambot.handlers;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.configurations.TelegramUser;
import service.configurations.TelegramUsers;
import service.telegrambot.commands.Commands;
import service.telegrambot.keyboards.InlineKeyboardMaker;
import utils.Utils;
import utils.WriteToLog;
import utils.yaml.YamlParser;

import java.util.List;

import static org.apache.log4j.Logger.getLogger;
import static service.telegrambot.commands.CommandsEnum.COMMAND_START;
import static service.telegrambot.commands.CommandsFactory.getInstance;
import static utils.Thesaurus.FilesNameYaml.TELEGRAM_USERS_CONFIG_YAML;

class MessageHandler implements Handler {
    private static final Logger log = getLogger(MessageHandler.class);

    @Override
    public SendMessage getMessage(Update update) {
        Message message = update.getMessage();

        final String chatId = message.getChatId().toString();

        if (validateUser(chatId)) {
            String data = message.getText();
            log.debug("InputData: " + "chatId=" + chatId + " data=" + data);

            Commands commands = getInstance(data);
            SendMessage sendMessage = commands.execute(chatId, data);

            if (data.equals(COMMAND_START.getValue())) {
                InlineKeyboardMaker inlineKeyboardMaker = new InlineKeyboardMaker();
                sendMessage.setReplyMarkup(inlineKeyboardMaker.createInlineKeyboard());
            }

            log.debug("OutputData: " + "chatId=" + sendMessage.getChatId() + " data=" + sendMessage.getText());

            logger(update, "user_requests");

            return sendMessage;
        }

        return getSendMessageToBlockedUser(update, chatId);
    }

    private void logger(Update update, String fileName) {
        WriteToLog writeToLog = new WriteToLog("telegram", fileName);
        final String chatId = update.getMessage().getChatId().toString();
        String data = update.getMessage().getText();
        String firstName = update.getMessage().getFrom().getFirstName();
        String userName = update.getMessage().getFrom().getUserName();
        writeToLog.writeLog("chatId: " + chatId + " data=" + data + " telegramUserName: " + userName + " firstName: " + firstName);
    }

    @NotNull
    private SendMessage getSendMessageToBlockedUser(Update update, String chatId) {
        logger(update, "blocked_users");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вам не разрешено пользоваться этим ботом, свяжитесь с разработчиком");
        return sendMessage;
    }

    private boolean validateUser(String chatId) {
        List<TelegramUser> userList = getUserList();
        if (userList == null) return false;

        return userList.stream().anyMatch(user -> chatId.equals(user.getTelegramId()));
    }

    @Nullable
    private List<TelegramUser> getUserList() {
        TelegramUsers telegramUsers = YamlParser.parse(TelegramUsers.class, TELEGRAM_USERS_CONFIG_YAML);

        List<TelegramUser> userList = telegramUsers.getTelegramUsers();
        if (Utils.nullOrEmpty(userList)) return null;
        return userList;
    }
}
