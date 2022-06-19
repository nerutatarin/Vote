package service.configurations;

import java.util.LinkedHashMap;
import java.util.Map;

import static utils.Thesaurus.FilesNameYaml.BROWSER_CONFIG_YAML;
import static utils.yaml.YamlParser.yamlParser;

public class BrowserConfig {

    private ProxySettings proxySettings;

    private Map<String, Browsers> browsers = new LinkedHashMap<>();

    public ProxySettings getProxySettings() {
        return proxySettings;
    }

    public void setProxySettings(ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
    }

    public Map<String, Browsers> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(Map<String, Browsers> browsers) {
        this.browsers = browsers;
    }

    public BrowserConfig parse() {
        return yamlParser(getClass(), BROWSER_CONFIG_YAML);
    }
}
