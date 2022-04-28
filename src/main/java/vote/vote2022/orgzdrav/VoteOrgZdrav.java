package vote.vote2022.orgzdrav;

import vote.VoteImpl;
import vote.browsers.BrowsersImpl;

import java.util.ArrayList;

public class VoteOrgZdrav extends VoteImpl {
    protected int voteCount = 10000;

    public void vote(BrowsersImpl browser) {
        pageManagerImpl = new PageManagerOrgZdrav(browser);
        pageManagerImpl.startPage(getBaseUrl());
        pageManagerImpl.btnVote();
        //writeToLog(getIpAddress());
    }

    @Override
    protected int getVoteCount() {
        return voteCount;
    }

    @Override
    protected String getIpAddress() {
        return null;
    }

    @Override
    protected String getMyIpUrl() {
        return "https://myip.ru/";
    }

    @Override
    protected String getCssSelector() {
        return "#ipcontent > table > tbody > tr:nth-child(2) > td";
    }

    @Override
    protected String getBaseUrl() {
        return "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";
    }

    @Override
    protected ArrayList<String> getInputs() {
        ArrayList<String> inputs = new ArrayList<>();
        return inputs;
    }
}
