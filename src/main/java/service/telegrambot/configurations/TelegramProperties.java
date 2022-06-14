package service.telegrambot.configurations;

import static utils.parsers.YamlParser.yamlParser;

public class TelegramProperties {
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

    public TelegramProperties parse() {
        return yamlParser(getClass(), "telegram_properties.yaml");
    }

    @Override
    public String toString() {
        return "TelegramProperties{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
