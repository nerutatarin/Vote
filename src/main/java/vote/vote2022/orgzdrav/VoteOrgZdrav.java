package vote.vote2022.orgzdrav;

import utils.WriteToLog;
import vote.VoteImpl;
import vote.browsers.Browsers;

import java.util.List;

import static utils.WriteToLog.writeToLog;

public class VoteOrgZdrav extends VoteImpl {
    protected int voteCount = 10000;
    protected String baseUrl = "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";

    public VoteOrgZdrav(List<Browsers> browsers) {
        this.browsers = browsers;
    }

    public VoteOrgZdrav(Browsers browser) {
        this.browser = browser;
    }

    @Override
    public void init() {
        if (browsers.isEmpty()) {
            vote(browser);
        } else {
            browsers.forEach(this::vote);
        }
    }

    @Override
    public void vote(Browsers browsers) {
        pageManager = new PageManagerOrgZdrav(browser);
        pageManager.votePage(getBaseUrl());
        pageManager.voteButton();
        writeToLog(pageManager);
    }

    @Override
    protected int getVoteCount() {
        return voteCount;
    }

    @Override
    protected String getMyIpUrl() {
        return myIpUrl;
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }
}
