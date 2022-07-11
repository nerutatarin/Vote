package votes.kp;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;
import service.pagemanager.parserpage.ParserPageAfterVoting;
import service.pagemanager.parserpage.ParserPageBeforeVoting;
import service.pagemanager.parserpage.ParserPageImpl;
import service.webdriver.model.Process;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(WebDriver webDriver, Process process, List<service.configurations.Member> members) {
        super(webDriver, process, members);
    }

    public PageManagerKP(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    @NotNull
    @Override
    protected String getPageTitle() {
        return "Клиника года - 2022. Уфа.";
    }

    @Override
    protected void allowInputs() {
        VotingPage votingPage = getPageBeforeVoting(getPageSource());
        if (votingPage == null) return;

        votingPage.getMembers().forEach(this::isAllowNomination);
    }

    @Override
    protected VotingPage getPageBeforeVoting(Document pageSource) {
        ParserPageImpl parserPage = new ParserPageBeforeVoting();
        return parserPage.getPageVoteMap(pageSource);
    }

    private void isAllowNomination(String nomination, List<Member> members) {
        if (getAllowMembers().containsKey(nomination)) {
            getInputsForAllowMembers(members);
        }
    }

    protected Map<String, String> getAllowMembers() {
        if (members == null || members.isEmpty()) {
            return memberConfig.getAllowMembers();
        }

        return members.stream()
                .collect(Collectors.toMap(service.configurations.Member::getNomination, service.configurations.Member::getTitle, (a, b) -> b, LinkedHashMap::new));
    }

    private void getInputsForAllowMembers(List<Member> members) {
        members.stream()
                .filter(member -> getAllowMembers().containsValue(member.getTitle()))
                .map(service.pagemanager.model.Member::getInput)
                .forEach(inputs::add);
    }

    @Override
    protected VotingPage getPageAfterVoting(Document pageSource) {
        ParserPageImpl parserPage = new ParserPageAfterVoting();
        return parserPage.getPageVoteMap(pageSource);
    }

    @Override
    protected By getButtonLocator() {
        return id("submit_vote");
    }
}
