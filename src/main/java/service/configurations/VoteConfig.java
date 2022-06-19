package service.configurations;

import static utils.Thesaurus.FilesNameYaml.VOTE_CONFIG_YAML;
import static utils.yaml.YamlParser.yamlParser;

public class VoteConfig {

    private VoteMode voteMode;

    public VoteMode getVoteMode() {
        return voteMode;
    }

    public void setVoteMode(VoteMode voteMode) {
        this.voteMode = voteMode;
    }

    public VoteConfig parse() {
        return yamlParser(getClass(), VOTE_CONFIG_YAML);
    }

    @Override
    public String toString() {
        return "VoteConfig{" +
                "voteMode=" + voteMode +
                '}';
    }
}
