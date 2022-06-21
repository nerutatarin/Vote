package service.telegrambot.configurations;

import utils.yaml.YamlParser;

import static utils.Thesaurus.FilesNameYaml.TELEGRAM_CONFIG_YAML;

public class TelegramConfig {
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

    public TelegramConfig parse() {
        return YamlParser.parse(getClass(), TELEGRAM_CONFIG_YAML);
    }

    @Override
    public String toString() {
        return "TelegramConfig{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
