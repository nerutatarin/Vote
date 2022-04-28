package vote.vote2022.kp;

import vote.browsers.BrowsersImpl;
import vote.pagemanager.PageManagerImpl;

import java.util.List;

import static java.util.Arrays.asList;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(BrowsersImpl browser) {
        this.webDriver = browser.getWebDriver();
        this.process = browser.getProcess();
    }

    @Override
    protected String getButtonLocator() {
        return "submit_vote";
    }

    @Override
    protected String getIpAddressLocator() {
        return "#ipcontent > table > tbody > tr:nth-child(2) > td";
    }

    @Override
    protected List<String> getInputsListLocatorById() {
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

        return asList(G1_KUVATOVO,
                G2_RKIB,
                G1_KUVATOVO,
                G3_DRKB,
                G4_GB1,
                G5_RD3,
                G9_BUBNOVSKY,
                G13_DP4,
                G14_KUVATOVO,
                G15_BUBNOVSKY,
                G16_KUVATOVO);
    }
}
