package votes.kp;

import org.jsoup.nodes.Document;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import service.configurations.Participants;
import service.pagemanager.PageManagerImpl;
import service.pagemanager.model.ParticipantVote;
import service.pagemanager.model.ResultsVote;
import service.webdriver.model.Process;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.openqa.selenium.By.id;

public class PageManagerKP extends PageManagerImpl {

    public PageManagerKP(WebDriver webDriver) {
        super(webDriver);
    }

    public PageManagerKP(WebDriver webDriver, Process process) {
        super(webDriver, process);
    }

    @Override
    protected By getButtonLocator() {
        return id("submit_vote");
    }

    @Override
    protected ResultsVote getResultsVote(Document pageSource) {
        return null;
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

    private Map<String, String> getAllowParticipants() {
        return participants
                .getParticipants()
                .stream()
                .filter(Participants.Participant::getAllow)
                .collect(Collectors.toMap(Participants.Participant::getNomination, Participants.Participant::getTitle, (a, b) -> b, LinkedHashMap::new));
    }
}
