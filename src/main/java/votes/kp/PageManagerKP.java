package votes.kp;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.webdriver.model.Process;
import service.configurations.Participants;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.PageVoteMap;
import service.pagemanager.model.ParticipantVote;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.ResultsVote;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(WebDriver webDriver) {
        super(webDriver);
    }

    public PageManagerKP(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    protected PageVoteMap getVotePages(Document pageSource) {
        PageVoteMap pageVoteMap = new PageVoteMap();
        pageVoteMap.setTimeStamp(new Date());
        pageVoteMap.setParticipantsMap(getParticipantVoteMap(pageSource));
        return pageVoteMap;
    }

    private Map<String, List<ParticipantVote>> getParticipantVoteMap(Document pageSource) {
        Map<String, List<ParticipantVote>> participantVoteMap = new LinkedHashMap<>();
        for (Element questionData : getQuestionData(pageSource)) {
            Elements question = questionData.getElementsByClass("question");
            participantVoteMap.put(question.text(), getParticipantVoteList(questionData));
        }
        return participantVoteMap;
    }

    private List<ParticipantVote> getParticipantVoteList(Element questionData) {
        List<ParticipantVote> participantVoteList = new ArrayList<>();
        for (Element label : getLabels(questionData)) {
            participantVoteList.add(getParticipantVote(label));
        }
       return participantVoteList;
    }

    @NotNull
    private Elements getLabels(Element questionData) {
        return questionData.getElementsByTag("label");
    }

    @NotNull
    private ParticipantVote getParticipantVote(Element label) {
        String participant = label.ownText();
        String inputId = label.attr("for");

        ParticipantVote participantVote = new ParticipantVote();
        participantVote.setId(Integer.parseInt(inputId.substring(3)));
        participantVote.setInput(inputId);
        participantVote.setTitle(Utils.removeUTF8BOM(participant));

        return participantVote;
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
        List<String> inputs = new ArrayList<>();
        pageVoteMap.getParticipantsMap().forEach((key, value) -> isAllowNomination(inputs, key, value));
        return inputs;
    }

    private void isAllowNomination(List<String> inputs, String nomination, List<ParticipantVote> participantVotes) {
        if (getAllowParticipants().containsKey(nomination)) {
            getInputsForAllowParticipants(inputs, participantVotes);
        }
    }

    private void getInputsForAllowParticipants(List<String> inputs, List<ParticipantVote> participantVotes) {
        participantVotes.stream().filter(participant -> getAllowParticipants().containsValue(participant.getTitle())).map(ParticipantVote::getInput).forEach(inputs::add);
    }

    @Override
    protected ResultsVote getResultsVote(Document pageSource) {
        ResultsVote resultsVote = new ResultsVote();

        List<ResultVote> resultVoteList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            Element pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpaceByRegex(pollResultsCount);
            String percent = Utils.substringAfterSpaceByRegex(pollResultsCount);

            ResultVote resultVote = new ResultVote();
            resultVote.setId(id++);
            if (pollResultAnswerTitle != null)
                resultVote.setTitle(Utils.removeUTF8BOM(pollResultAnswerTitle.ownText()));
            resultVote.setCount(count);
            resultVote.setPercent(percent);

            resultVoteList.add(resultVote);
        }

        resultsVote.setTimeStamp(new Date());
        resultsVote.setResultVotes(resultVoteList);
        return resultsVote;
    }

    private Elements getPollResultsAnswer(Document pageSource) {
        return pageSource.getElementsByClass("unicredit_poll_results_answer");
    }

    private Map<String, String> getAllowParticipants() {
        return participants
                .getParticipants()
                .stream()
                .filter(Participants.Participant::getAllow)
                .collect(Collectors.toMap(Participants.Participant::getNomination, Participants.Participant::getTitle, (a, b) -> b, LinkedHashMap::new));
    }
}
