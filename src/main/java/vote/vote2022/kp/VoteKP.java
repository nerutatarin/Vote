package vote.vote2022.kp;

import vote.VoteImpl;
import vote.browsers.Browsers;

import java.util.List;

public class VoteKP extends VoteImpl {
    protected int voteCount = 1;
    protected String baseUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";

    public VoteKP(List<Browsers> browsers) {
        this.browsers = browsers;
    }

    public VoteKP(Browsers browser) {
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

    public void vote(Browsers browser) {
        pageManager = new PageManagerKP(browser);
        pageManager.votePage(getBaseUrl());
        pageManager.voteInput();
        pageManager.voteButton();

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
