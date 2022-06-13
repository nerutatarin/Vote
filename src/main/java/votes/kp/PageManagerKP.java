package votes.kp;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.browsers.model.Process;
import service.configurations.Participants;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.PageVote;
import service.pagemanager.model.ParticipantVote;
import service.pagemanager.model.ResultsVote;
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

    protected void getVotePages(Document pageSource, List<PageVote> pageVotes) {
        Elements questionData = getQuestionData(pageSource);
        for (Element question : questionData) {
            String title = question.selectFirst("div.question").text();

            List<ParticipantVote> participantVoteList = getParticipantsOfNominations(question);

            PageVote pageVote = new PageVote();
            pageVote.setTitleNomination(title.substring(3).trim());
            pageVote.setParticipant(participantVoteList);

            pageVotes.add(pageVote);
        }
    }

    private List<ParticipantVote> getParticipantsOfNominations(Element question) {
        Elements answers = question.getElementsByClass("answers");
        return answers.stream()
                .findFirst()
                .map(this::getParticipants)
                .orElse(new ArrayList<>());
    }

    private List<ParticipantVote> getParticipants(Element answer) {
        Elements labels = answer.select("label");
        List<ParticipantVote> participantVotes = new ArrayList<>();
        for (Element el : labels) {
            String inputMO = el.attr("for");
            String titleMO = el.ownText();

            ParticipantVote participantVote = new ParticipantVote();
            participantVote.setId(Integer.parseInt(inputMO.substring(3)));
            participantVote.setInput(inputMO);
            participantVote.setTitleMO(titleMO);

            participantVotes.add(participantVote);
        }
        return participantVotes;
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
        for (PageVote pageVote : pageVoteList) {
            List<ParticipantVote> participantVoteList = pageVote.getParticipant();
            for (ParticipantVote participantVote : participantVoteList) {
                for (Participants.Participant allow : getAllowParticipants()) {
                    String titleNomination = pageVote.getTitleNomination();
                    if (allow.getNomination().contains(titleNomination) && allow.getTitle().contains(participantVote.getTitleMO())) {
                        String input = participantVote.getInput();
                        list.add(input);
                    }
                }

            }
        }
        return list;
    }

    @Override
    protected List<ResultsVote> getVoteCountList(Document pageSource) {
        List<ResultsVote> resultsVoteList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            Element pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpace(pollResultsCount);
            String percent = Utils.substringAfterSpace(pollResultsCount);

            ResultsVote resultsVote = new ResultsVote();
            resultsVote.setId(id++);
            if (pollResultAnswerTitle != null) resultsVote.setTitle(Utils.removeUTF8BOM(pollResultAnswerTitle.ownText()));
            resultsVote.setCount(count);
            resultsVote.setPercent(percent);

            resultsVoteList.add(resultsVote);
        }
        return resultsVoteList;
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
