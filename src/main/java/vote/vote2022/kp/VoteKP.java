package vote.vote2022.kp;

import utils.ipaddress.IPAddressGetter;
import utils.retrofit.services.webproxy.freeproxyapi.FreeProxyService;
import utils.retrofit.services.webproxy.freeproxyapi.response.FreeProxyMini;
import vote.VoteImpl;
import vote.browsers.Browsers;

import java.util.List;

public class VoteKP extends VoteImpl {
    private final String voteUrl = "https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022";

    public VoteKP(List<Browsers> browsers, int count) {
        this.browsersList = browsers;
        this.count = count;
    }

    public VoteKP(Browsers browser, int count) {
        this.browser = browser;
        this.count = count;
    }

    public void vote(Browsers browser) {
        webDriver = browser.getWebDriver();
        process = browser.getProcess();

        myIpAddress = IPAddressGetter.getIpAddressJson(webDriver, process, ipAddrUrl);

        FreeProxyService webProxyService = new FreeProxyService();
        FreeProxyMini freeProxyMini = webProxyService.getProxyMini();
        System.out.println(freeProxyMini);

        pageManager = new PageManagerKP(webDriver, process, myIpAddress);
        pageManager.votePage(voteUrl);
        pageManager.voteInput();
        pageManager.voteButton();
        pageManager.voteLogging();
    }
}
