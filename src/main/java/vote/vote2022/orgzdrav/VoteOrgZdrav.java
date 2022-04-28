package vote.vote2022.orgzdrav;

import vote.VoteImpl;
import vote.browsers.BrowsersImpl;

public class VoteOrgZdrav extends VoteImpl {
    protected int voteCount = 10000;
    protected String baseUrl = "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";

    public void vote(BrowsersImpl browser) {
        pageManagerImpl = new PageManagerOrgZdrav(browser);
        pageManagerImpl.votePage(getBaseUrl());
        pageManagerImpl.voteButton();
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
