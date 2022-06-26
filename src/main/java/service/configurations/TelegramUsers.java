package service.configurations;

import java.util.List;

import static utils.Thesaurus.FilesNameYaml.TELEGRAM_USERS_CONFIG_YAML;

public class TelegramUsers extends Config{
    private List<TelegramUser> telegramUsers;

    public List<TelegramUser> getTelegramUsers() {
        return telegramUsers;
    }

    public void setTelegramUsers(List<TelegramUser> telegramUsers) {
        this.telegramUsers = telegramUsers;
    }

    @Override
    public String toString() {
        return "TelegramUsers{" +
                "telegramUsers=" + telegramUsers +
                '}';
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return TELEGRAM_USERS_CONFIG_YAML;
    }
}
