package vote.vote2022.kp;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.Utils;
import utils.ipaddress.model.MyIpAddress;
import vote.browsers.model.Process;
import vote.pagemanager.PageManagerImpl;
import vote.pagemanager.model.Participant;
import vote.pagemanager.model.VoteCount;
import vote.pagemanager.model.VotePage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {
    private final String RKIB = "МЗ РБ ГБУЗ Республиканская клиническая инфекционная больница";
    private final String RD3 = "ГБУЗ РБ Родильный дом № 3 г. Уфа";
    private final String DP4 = "ГБУЗ РБ Детская поликлиника № 4 г. Уфа";
    private final List<String> participants = asList(RKIB, RD3, DP4);

    public PageManagerKP(WebDriver webDriver, Process process, MyIpAddress myIpAddress) {
        this.webDriver = webDriver;
        this.process = process;
        this.myIpAddress = myIpAddress;
        getBrowserName();
    }

    protected void getVotePages(Document pageSource, List<VotePage> votePages) {
        Elements questionData = getQuestionData(pageSource);
        for (Element question : questionData) {
            String title = question.selectFirst("div.question").text();

            List<Participant> participantList = getParticipantsOfNominations(question);

            VotePage votePage = new VotePage();
            votePage.setTitleNomination(title);
            votePage.setParticipant(participantList);

            votePages.add(votePage);
        }
    }

    private List<Participant> getParticipantsOfNominations(Element question) {
        Elements answers = question.getElementsByClass("answers");
        return answers.stream()
                .findFirst()
                .map(this::getParticipants)
                .orElse(new ArrayList<>());
    }

    private List<Participant> getParticipants(Element answer) {
        Elements labels = answer.select("label");
        List<Participant> participants = new ArrayList<>();
        for (Element el : labels) {
            String inputMO = el.attr("for");
            String titleMO = el.ownText();

            Participant participant = new Participant();
            participant.setId(Integer.parseInt(inputMO.substring(3)));
            participant.setInput(inputMO);
            participant.setTitleMO(titleMO);

            participants.add(participant);
        }
        return participants;
    }

    private Elements getQuestionData(Document pageSource) {
        return pageSource.getElementsByClass("questiondata");
    }

    @Override
    protected By getButtonLocator() {
        return id("submit_vote");
    }

    @Override
    protected List<String> getInputsListLocatorById() {
        return votePageList.stream()
                .map(VotePage::getParticipant)
                .flatMap(Collection::stream)
                .filter(participant -> participants.contains(participant.getTitleMO()))
                .map(Participant::getInput)
                .collect(Collectors.toList());
    }

    @Override
    protected List<VoteCount> getVoteCountList(Document pageSource) {
        List<VoteCount> voteCountList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            String pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpace(pollResultsCount);
            String percent = Utils.substringAfterSpace(pollResultsCount);

            VoteCount voteCount = new VoteCount();
            voteCount.setId(id++);
            voteCount.setTitle(pollResultAnswerTitle);
            voteCount.setCount(count);
            voteCount.setPercent(percent);

            voteCountList.add(voteCount);
        }
        return voteCountList;
    }

    private Elements getPollResultsAnswer(Document pageSource) {
        return pageSource.getElementsByClass("unicredit_poll_results_answer");
    }
}
