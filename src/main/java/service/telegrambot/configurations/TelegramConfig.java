package service.telegrambot.configurations;

import static utils.Thesaurus.FilesNameYaml.TELEGRAM_CONFIG_YAML;
import static utils.yaml.YamlParser.yamlParser;

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
        return yamlParser(getClass(), TELEGRAM_CONFIG_YAML);
    }

    @Override
    public String toString() {
        return "TelegramConfig{" +
                "name='" + name + '\'' +
                ", token='" + token + '\'' +
                '}';
    }
}
