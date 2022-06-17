package votes.kp;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.PageVoteMap;
import service.pagemanager.model.ParticipantVote;
import utils.jackson.JsonMapper;

import java.util.*;

public abstract class ParserPageImpl {

    public PageVoteMap getPageVoteMap(Document pageSource) {
        PageVoteMap pageVoteMap = new PageVoteMap();

        pageVoteMap.setTimeStamp(new Date());

        Map<String, List<ParticipantVote>> participantVoteMap = getParticipantVoteMap(pageSource);
        if (participantVoteMap == null) return pageVoteMap;

        pageVoteMap.setParticipantsMap(participantVoteMap);

        savePageVote(pageVoteMap);

        return pageVoteMap;
    }

    protected abstract void savePageVote(PageVoteMap pageVoteMap);

    @Nullable
    private Map<String, List<ParticipantVote>> getParticipantVoteMap(Document pageSource) {
        Elements questionsData = getQuestionData(pageSource);
        if (questionsData.isEmpty()) return null;

        Map<String, List<ParticipantVote>> participantVoteMap = new LinkedHashMap<>();
        for (Element questionData : questionsData) {
            Elements question = getQuestion(questionData);
            if (question.isEmpty()) return null;

            List<ParticipantVote> participantVoteList = getParticipantVoteList(questionData);
            JsonMapper.objectListToFilePretty(participantVoteList, "participants.json");

            if (participantVoteList.isEmpty()) return null;

            participantVoteMap.put(question.text(), participantVoteList);
        }
        return participantVoteMap;
    }

    private Elements getQuestionData(Document pageSource) {
        return pageSource.getElementsByClass("questiondata");
    }

    @NotNull
    private Elements getQuestion(Element questionData) {
        return questionData.getElementsByClass("question");
    }

    @NotNull
    private List<ParticipantVote> getParticipantVoteList(Element questionData) {
        List<ParticipantVote> participantVoteList = new ArrayList<>();

        Elements elements = getElements(questionData);
        if (elements.isEmpty()) return participantVoteList;

        for (Element element : elements) {
            participantVoteList.add(getParticipantVote(element));
        }
        return participantVoteList;
    }

    protected abstract ParticipantVote getParticipantVote(Element label);

    protected abstract Elements getElements(Element questionData);
}
