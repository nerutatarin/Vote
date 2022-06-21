package service.configurations;

import utils.yaml.YamlParser;

import static utils.Thesaurus.FilesNameYaml.VOTE_CONFIG_YAML;

public class VoteConfig {

    private VoteMode voteMode;

    public VoteMode getVoteMode() {
        return voteMode;
    }

    public void setVoteMode(VoteMode voteMode) {
        this.voteMode = voteMode;
    }

    public VoteConfig parse() {
        return YamlParser.parse(getClass(), VOTE_CONFIG_YAML);
    }

    @Override
    public String toString() {
        return "VoteConfig{" +
                "voteMode=" + voteMode +
                '}';
    }
}
