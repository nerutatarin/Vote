package service.configurations;

import utils.yaml.YamlParser;

public abstract class Config {
    protected abstract <T extends Config> Class<T> getConfigClass();

    protected abstract String getResource();

    public <T extends Config> T parse() {
        return YamlParser.parse(getConfigClass(), getResource());
    }
}
