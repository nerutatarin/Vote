package votes.kp.model;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.PageVoteMap;
import service.pagemanager.model.ParticipantVote;
import utils.Utils;
import utils.jackson.JsonMapper;
import votes.kp.ParserPageImpl;

public class ParserAfterPage extends ParserPageImpl {

    @Override
    protected Elements getElements(Element questionData) {
        return questionData.getElementsByClass("unicredit_poll_results_answer");
    }

    @Override
    protected void savePageVote(PageVoteMap pageVoteMap) {
        JsonMapper.objectToFilePretty(pageVoteMap, "after_page_vote.json");
    }

    @NotNull
    protected ParticipantVote getParticipantVote(Element element) {
        String pollResultAnswerTitle = element.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
        String pollResultsCount = element.select("span.unicredit_poll_results_count").text();

        String count = Utils.substringBeforeSpaceByRegex(pollResultsCount);
        String percent = Utils.substringAfterSpaceByRegex(pollResultsCount);

        ParticipantVote participantVote = new ParticipantVote();
        participantVote.setTitle(Utils.removeUTF8BOM(pollResultAnswerTitle));
        participantVote.setCount(count);
        participantVote.setPercent(percent);

        return participantVote;
    }
}
