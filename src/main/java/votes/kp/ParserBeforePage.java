package votes.kp;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.PageVoteMap;
import service.pagemanager.model.ParticipantVote;
import utils.Utils;
import utils.jackson.JsonMapper;

public class ParserBeforePage extends ParserPageImpl {

    @NotNull
    protected Elements getElements(Element questionData) {
        return questionData.getElementsByTag("label");
    }

    @Override
    protected void savePageVote(PageVoteMap pageVoteMap) {
        JsonMapper.objectToFilePretty(pageVoteMap, "before_page_vote.json");
    }

    @NotNull
    protected ParticipantVote getParticipantVote(Element element) {
        String participant = element.ownText();
        String inputId = element.attr("for");

        ParticipantVote participantVote = new ParticipantVote();
        participantVote.setId(Integer.parseInt(inputId.substring(3)));
        participantVote.setInput(inputId);
        participantVote.setTitle(Utils.removeUTF8BOM(participant));

        return participantVote;
    }
}
