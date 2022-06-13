package service.configurations;

import java.util.LinkedHashMap;
import java.util.Map;

import static utils.parsers.YamlParser.yamlParser;

public class BrowserProperties {

    private ProxySettings proxySettings;

    private Map<String, BrowserType> browsersType = new LinkedHashMap<>();

    public ProxySettings getProxySettings() {
        return proxySettings;
    }

    public void setProxySettings(ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
    }

    public Map<String, BrowserType> getBrowsersType() {
        return browsersType;
    }

    public void setBrowsersType(Map<String, BrowserType> browsersType) {
        this.browsersType = browsersType;
    }

    public BrowserProperties parse() {
        return yamlParser(getClass(), "browser_properties.yaml");
    }
}
