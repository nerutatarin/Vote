package service.configurations;

import static utils.Thesaurus.FilesNameYaml.VOTE_CONFIG_YAML;

public class VoteConfig extends Config {

    private VoteMode voteMode;

    public VoteMode getVoteMode() {
        return voteMode;
    }

    public void setVoteMode(VoteMode voteMode) {
        this.voteMode = voteMode;
    }

    @Override
    public String toString() {
        return "VoteConfig{" +
                "voteMode=" + voteMode +
                '}';
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return VOTE_CONFIG_YAML;
    }
}
