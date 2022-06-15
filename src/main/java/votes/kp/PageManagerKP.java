package votes.kp;

import org.jetbrains.annotations.NotNull;
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
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.ResultsVote;
import utils.Utils;

import java.util.ArrayList;
import java.util.Date;
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
            PageVote pageVote = createPageVote(question);
            pageVotes.add(pageVote);
        }
    }

    @NotNull
    private PageVote createPageVote(Element question) {
        PageVote pageVote = new PageVote();

        String nomination = question.selectFirst("div.question").text();
        List<ParticipantVote> participantVoteList = getParticipantsOfNominations(question);

        pageVote.setTimeStamp(new Date());
        pageVote.setNomination(nomination.substring(3).trim());
        pageVote.setParticipant(participantVoteList);
        return pageVote;
    }

    private List<ParticipantVote> getParticipantsOfNominations(Element question) {
        return getAnswers(question).stream()
                .findFirst()
                .map(this::getParticipants)
                .orElse(new ArrayList<>());
    }

    @NotNull
    private Elements getAnswers(Element question) {
        return question.getElementsByClass("answers");
    }

    private List<ParticipantVote> getParticipants(Element answer) {
        List<ParticipantVote> participantVotes = new ArrayList<>();

        for (Element label : getLabels(answer)) {
            String input = label.attr("for");
            String title = label.ownText();

            ParticipantVote participantVote = new ParticipantVote();
            participantVote.setId(Integer.parseInt(input.substring(3)));
            participantVote.setInput(input);
            participantVote.setTitle(title);

            participantVotes.add(participantVote);
        }
        return participantVotes;
    }

    @NotNull
    private Elements getLabels(Element answer) {
        return answer.select("label");
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
                    String titleNomination = pageVote.getNomination();
                    if (allow.getNomination().contains(titleNomination) && allow.getTitle().contains(participantVote.getTitle())) {
                        String input = participantVote.getInput();
                        list.add(input);
                    }
                }

            }
        }
        return list;
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
            if (pollResultAnswerTitle != null) resultVote.setTitle(Utils.removeUTF8BOM(pollResultAnswerTitle.ownText()));
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

    private List<Participants.Participant> getAllowParticipants() {
        return participants.getParticipants().stream()
                .filter(Participants.Participant::getAllow)
                .collect(Collectors.toList());
    }
}
