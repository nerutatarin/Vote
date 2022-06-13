package votes.kp;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.browsers.model.Process;
import service.configurations.Participants;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.Participant;
import service.pagemanager.model.ResultsCount;
import service.pagemanager.model.VotePage;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(WebDriver webDriver) {
        super(webDriver);
    }

    public PageManagerKP(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    protected void getVotePages(Document pageSource, List<VotePage> votePages) {
        Elements questionData = getQuestionData(pageSource);
        for (Element question : questionData) {
            String title = question.selectFirst("div.question").text();

            List<Participant> participantList = getParticipantsOfNominations(question);

            VotePage votePage = new VotePage();
            votePage.setTitleNomination(title.substring(3).trim());
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
        List<String> list = new ArrayList<>();
        for (VotePage votePage : votePageList) {
            List<Participant> participantList = votePage.getParticipant();
            for (Participant participant : participantList) {
                for (Participants.Participant allow : getAllowParticipants()) {
                    String titleNomination = votePage.getTitleNomination();
                    if (allow.getNomination().contains(titleNomination) && allow.getTitle().contains(participant.getTitleMO())) {
                        String input = participant.getInput();
                        list.add(input);
                    }
                }

            }
        }
        return list;
    }

    @Override
    protected List<ResultsCount> getVoteCountList(Document pageSource) {
        List<ResultsCount> resultsCountList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            String pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpace(pollResultsCount);
            String percent = Utils.substringAfterSpace(pollResultsCount);

            ResultsCount resultsCount = new ResultsCount();
            resultsCount.setId(id++);
            resultsCount.setTitle(pollResultAnswerTitle);
            resultsCount.setCount(count);
            resultsCount.setPercent(percent);

            resultsCountList.add(resultsCount);
        }
        return resultsCountList;
    }

    private Elements getPollResultsAnswer(Document pageSource) {
        return pageSource.getElementsByClass("unicredit_poll_results_answer");
    }

    private List<Participants.Participant> getAllowParticipants() {
        return participants.getParticipants().stream()
                .filter(Participants.Participant::getAllow)
                .collect(Collectors.toList());
    }
}
