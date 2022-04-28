package vote.vote2022.kp;

import vote.VoteImpl;
import vote.browsers.BrowsersImpl;

public class VoteKP extends VoteImpl {
    protected int voteCount = 10000;
    protected String baseUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";

    public void vote(BrowsersImpl browser) {
        pageManagerImpl = new PageManagerKP(browser);
        pageManagerImpl.votePage(getBaseUrl());
        pageManagerImpl.voteInput();
        pageManagerImpl.voteButton();
    }

    @Override
    protected String getMyIpUrl() {
        return myIpUrl;
    }

    @Override
    protected String getBaseUrl() {
        return baseUrl;
    }

    @Override
    protected int getVoteCount() {
        return voteCount;
    }
}
