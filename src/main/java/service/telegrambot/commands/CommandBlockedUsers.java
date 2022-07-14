package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.configurations.TelegramUser;
import utils.jackson.JsonMapper;

import static utils.Thesaurus.FilesNameJson.BLOCKED_USERS_JSON;

public class CommandBlockedUsers extends CommandsImpl{
    private static final Logger log = Logger.getLogger(CommandBlockedUsers.class);

    @Override
    protected StringBuilder replyMessageMake() {
        TelegramUser telegramUser = JsonMapper.fileToObject(BLOCKED_USERS_JSON, TelegramUser.class);
        if (telegramUser == null) return null;
        return new StringBuilder()
                .append(telegramUser.getTelegramId())
                .append("\n")
                .append(telegramUser.getTelegramUserName())
                .append("\n")
                .append(telegramUser.getTelegramFirstName());
    }

    @Override
    protected Logger getLog() {
        return log;
    }
}
