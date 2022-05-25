package vote.vote2022.kp;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.model.Process;
import vote.pagemanager.PageManagerImpl;
import vote.pagemanager.model.VoteCount;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;
import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {
    List<VoteCount> voteCountList = new ArrayList<>();

    public PageManagerKP(WebDriver webDriver, Process process, MyIpAddress myIpAddress) {
        this.webDriver = webDriver;
        this.process = process;
        this.myIpAddress = myIpAddress;
        getBrowserName();
    }

    @Override
    protected By getButtonLocator() {
        return id("submit_vote");
    }

    @Override
    protected List<String> getInputsListLocatorById() {
        String G1_KUVATOVO = "inp4";
        String G2_RKIB = "inp19";
        String G3_DRKB = "inp23";
        String G4_GB1 = "inp24";
        String G5_RD3 = "inp29";
        String G9_EUROOPTIK = "inp47";
        String G10_BUBNOVSKY = "inp49";
        String G14_DP4 = "inp59";
        String G15_STERLIT = "inp64";
        String G16_BUBNOVSKY = "inp66";
        String G17_KUVATOVO = "inp68";

        return asList(
                //G1_KUVATOVO,
                G2_RKIB
                //G3_DRKB,
                //G4_GB1,
                //G5_RD3,
                //G9_EUROOPTIK,
                //G10_BUBNOVSKY,
                //G14_DP4,
                //G15_STERLIT,
                //G16_BUBNOVSKY,
                //G17_KUVATOVO
        );
    }

    @Override
    protected List<VoteCount> getVoteCountList(Document pageSource) {

        Elements pollResultsAnswer = pageSource.getElementsByClass("unicredit_poll_results_answer");

        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            VoteCount voteCount = new VoteCount();
            voteCount.setId(id++);

            String name = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
            voteCount.setTitle(name);

            String count = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String[] arrCount = count.split(" ", 2);
            voteCount.setCount(arrCount[0]);
            voteCount.setPercent(arrCount[1].substring(1, arrCount[1].length() - 1));

            voteCountList.add(voteCount);
        }
        return voteCountList;
    }
}
