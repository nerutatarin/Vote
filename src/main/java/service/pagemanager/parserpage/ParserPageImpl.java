package service.pagemanager.parserpage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;

import java.util.*;

import static utils.Utils.nullOrEmpty;

public abstract class ParserPageImpl {
    protected int id = 1;

    public VotingPage getPageVoteMap(Document pageSource) {
        VotingPage votingPage = new VotingPage();

        votingPage.setTimeStamp(new Date());

        Map<String, List<Member>> members = getMembers(pageSource);
        if (nullOrEmpty(members)) return votingPage;

        votingPage.setMembers(members);

        savePageVote(votingPage);

        return votingPage;
    }

    protected abstract <T> void savePageVote(T object);

    @Nullable
    private Map<String, List<Member>> getMembers(Document pageSource) {
        Elements questionsData = getQuestionData(pageSource);
        if (nullOrEmpty(questionsData)) return null;

        Map<String, List<Member>> members = new LinkedHashMap<>();

        for (Element questionData : questionsData) {
            Elements question = getQuestion(questionData);
            if (nullOrEmpty(question)) return null;

            List<Member> memberList = getMemberList(questionData);

            if (nullOrEmpty(memberList)) return null;

            members.put(question.text(), memberList);
        }
        return members;
    }

    private Elements getQuestionData(Document pageSource) {
        return pageSource.getElementsByClass("questiondata");
    }

    @NotNull
    private Elements getQuestion(Element questionData) {
        return questionData.getElementsByClass("question");
    }

    @NotNull
    private List<Member> getMemberList(Element questionData) {
        Elements elements = getElements(questionData);

        List<Member> memberList = new ArrayList<>();
        if (nullOrEmpty(elements)) return memberList;
        //memberList = elements.stream().map(this::getMember).collect(toList());


        for (Element element : elements) {
            memberList.add(getMember(element));
        }

        return memberList;
    }

    protected abstract Member getMember(Element element);

    protected abstract Elements getElements(Element questionData);
}
