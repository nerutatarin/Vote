package vote.vote2022.orgzdrav;

import utils.ipaddress.IPAddressGetter;
import vote.VoteImpl;
import vote.browsers.Browsers;

import java.util.List;

public class VoteOrgZdrav extends VoteImpl {
    private final String voteUrl = "https://leader.orgzdrav.com/practices/effektivnoe-upravlenie-meditsinskimi-kadrami";

    public VoteOrgZdrav(List<Browsers> browsers, int count) {
        this.browsersList = browsers;
        this.count = count;
    }

    public VoteOrgZdrav(Browsers browser, int count) {
        this.browser = browser;
        this.count = count;
    }

    @Override
    public void vote(Browsers browser) {
        webDriver = browser.getWebDriver();
        process = browser.getProcess();

        myIpAddress = IPAddressGetter.getIpAddress(webDriver, process, ipAddrUrl);

        pageManager = new PageManagerOrgZdrav(webDriver, process);
        pageManager.votePage(voteUrl);
        pageManager.voteButton();
        pageManager.voteLogging();
    }
}
