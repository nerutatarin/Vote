package service.telegrambot.configurations;

import service.configurations.Config;

import static utils.Thesaurus.FilesNameYaml.TELEGRAM_CONFIG_YAML;

public class TelegramConfig extends Config {
    private String name;
    private String token;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TelegramConfig{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return TELEGRAM_CONFIG_YAML;
    }
}
