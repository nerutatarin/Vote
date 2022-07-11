package service.configurations;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static service.webdriver.BrowserFactory.getInstance;
import static utils.Thesaurus.FilesNameYaml.VOTE_CONFIG_YAML;

public class VoteConfig extends Config {
    private VoteMode voteMode;
    private Map<String, Browser> browserMap = new LinkedHashMap<>();

    public VoteMode getVoteMode() {
        return voteMode;
    }

    public void setVoteMode(VoteMode voteMode) {
        this.voteMode = voteMode;
    }

    public Map<String, Browser> getBrowserMap() {
        return browserMap;
    }

    public void setBrowserMap(Map<String, Browser> browserMap) {
        this.browserMap = browserMap;
    }

    public List<Browser> getAllowBrowserList() {
        return getBrowserMap().values()
                .stream()
                .filter(Browser::isEnable)
                .collect(toList());
    }

    public List<service.webdriver.Browser> getBrowsersInstance() {
        List<service.webdriver.Browser> list = new ArrayList<>();
        List<Browser> allowBrowserList = getAllowBrowserList();

        for (Browser browser : allowBrowserList) {
            service.webdriver.Browser instance = getInstance(browser.getName());
            list.add(instance);
        }
        return list;
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return VOTE_CONFIG_YAML;
    }

    @Override
    public String toString() {
        return "VoteConfig{" +
                "voteMode=" + voteMode +
                ", browserMap=" + browserMap +
                "} " + super.toString();
    }
}
