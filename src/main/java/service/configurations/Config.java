package service.configurations;

import utils.yaml.YamlParser;

import static utils.Utils.requireNonNull;

public abstract class Config {
    protected abstract <T extends Config> Class<T> getConfigClass();

    protected abstract String getResource();

    public <T extends Config> T parse() {
        return requireNonNull(YamlParser.parse(getConfigClass(), getResource()), "Не найден файл конфигурации: " + getResource());
    }
}