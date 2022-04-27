package vote.vote2022.kp;

import vote.Vote;
import vote.browsers.Browsers;

import java.util.ArrayList;

public class VoteKP extends Vote {
    protected int voteCount = 10000;

    public void vote(Browsers browser) {
        pageManager = new PageManagerKP(browser);
        pageManager.startPage(getBaseUrl());
        pageManager.chkVoteMo(getInputs());
        pageManager.btnVote();
        //writeToLog(getIpAddress());
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
        return "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";
    }

    @Override
    protected int getVoteCount() {
        return voteCount;
    }

    @Override
    protected String getIpAddress() {
        /*IPAddressGetter ipAddressGetter = new IPAddressGetter(webDriver);
        return ipAddressGetter.getIpAddress(getCssSelector(), getMyIpUrl());*/
        return null;
    }

    @Override
    protected ArrayList<String> getInputs() {
        ArrayList<String> inputs = new ArrayList<>();

        String G1_KUVATOVO = "inp4";
        String G2_RKIB = "inp19";
        String G3_DRKB = "inp23";
        String G4_GB1 = "inp24";
        String G5_RD3 = "inp29";
        String G9_BUBNOVSKY = "inp49";
        String G13_DP4 = "inp59";
        String G14_KUVATOVO = "inp63";
        String G15_BUBNOVSKY = "inp67";
        String G16_KUVATOVO = "inp69";

        inputs.add(G1_KUVATOVO);
        inputs.add(G2_RKIB);
        inputs.add(G3_DRKB);
        //inputs.add(G4_GB1);
        //inputs.add(G5_RD3);
        inputs.add(G9_BUBNOVSKY);
        //inputs.add(G13_DP4);
        inputs.add(G14_KUVATOVO);
        inputs.add(G15_BUBNOVSKY);
        inputs.add(G16_KUVATOVO);

        return inputs;
    }
}
