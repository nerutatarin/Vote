package service.configurations;

import java.util.LinkedHashMap;
import java.util.Map;

import static utils.Thesaurus.FilesNameYaml.BROWSER_CONFIG_YAML;

public class BrowsersConfig extends Config {

    private ProxySettings proxySettings;

    private Map<String, Browser> browserMap = new LinkedHashMap<>();

    public ProxySettings getProxySettings() {
        return proxySettings;
    }

    public void setProxySettings(ProxySettings proxySettings) {
        this.proxySettings = proxySettings;
    }

    public Map<String, Browser> getBrowserMap() {
        return browserMap;
    }

    public void setBrowserMap(Map<String, Browser> browserMap) {
        this.browserMap = browserMap;
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return BROWSER_CONFIG_YAML;
    }
}
