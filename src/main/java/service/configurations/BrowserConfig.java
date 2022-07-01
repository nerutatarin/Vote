package service.configurations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Thesaurus.FilesNameYaml.BROWSER_CONFIG_YAML;

public class BrowserConfig extends Config {

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

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return BROWSER_CONFIG_YAML;
    }

    public List<String> getEnabledBrowsers() {
        return browsers.entrySet().stream()
                .filter(browsersEntry -> browsersEntry.getValue().isEnable())
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }
}
