package service.telegrambot.handlers;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import service.configurations.TelegramUser;
import service.configurations.TelegramUsers;
import utils.Utils;
import utils.WriteToLog;
import utils.jackson.JsonMapper;
import utils.yaml.YamlParser;

import java.util.List;

import static utils.Thesaurus.FilesNameJson.BLOCKED_USERS_JSON;
import static utils.Thesaurus.FilesNameYaml.TELEGRAM_USERS_CONFIG_YAML;

public abstract class HandlerImpl {

    protected abstract Logger getLog();

    @NotNull
    protected SendMessage getSendMessageToBlockedUser(Update update, String chatId) {
        UserInfo userInfo = getUserInfo(update);
        JsonMapper.objectToFilePretty(userInfo, BLOCKED_USERS_JSON);

        logger(update, "blocked_users");

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText("Вам не разрешено пользоваться этим ботом, свяжитесь с разработчиком");
        return sendMessage;
    }

    protected void logger(Update update, String fileName) {
        UserInfo userInfo = getUserInfo(update);

        WriteToLog writeToLog = new WriteToLog("telegram", fileName);
        writeToLog.writeLog(JsonMapper.toJson(userInfo));
    }

    @NotNull
    private UserInfo getUserInfo(Update update) {
        String text = update.getMessage().getText();
        String chatId = update.getMessage().getFrom().getId().toString();
        String userName = update.getMessage().getFrom().getUserName();
        String firstName = update.getMessage().getFrom().getFirstName();

        TelegramUser telegramUser = new TelegramUser();
        telegramUser.setTelegramId(chatId);
        telegramUser.setTelegramUserName(userName);
        telegramUser.setTelegramFirstName(firstName);

        UserInfo userInfo = new UserInfo();
        userInfo.setText(text);
        userInfo.setTelegramUser(telegramUser);
        return userInfo;
    }

    protected boolean validateUser(String chatId) {
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

    private static class UserInfo {
        private String text;
        private TelegramUser telegramUser;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public TelegramUser getTelegramUser() {
            return telegramUser;
        }

        public void setTelegramUser(TelegramUser telegramUser) {
            this.telegramUser = telegramUser;
        }

        @Override
        public String toString() {
            return "{" +
                    "text:" + text + '\'' +
                    ", telegramUser:" + telegramUser +
                    '}';
        }
    }
}
