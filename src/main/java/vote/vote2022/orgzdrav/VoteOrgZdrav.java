package vote.vote2022.orgzdrav;

import vote.Vote;
import vote.browsers.Browsers;

import java.util.ArrayList;

public class VoteOrgZdrav extends Vote {
    protected int voteCount = 10000;

    public void vote(Browsers browser) {
        pageManager = new PageManagerOrgZdrav(browser);
        pageManager.startPage(getBaseUrl());
        pageManager.btnVote();
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
