package service.pagemanager.parserpage;

import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.Member;

import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.Utils.*;
import static utils.jackson.JsonMapper.objectToFilePretty;

/**
 * Парсер страницы после голосования
 */

public class ParserPageAfterVoting extends ParserPageImpl {

    @Override
    protected Elements getElements(Element questionData) {
        return questionData.getElementsByClass("unicredit_poll_results_answer");
    }

    @Override
    protected <T> void savePageVote(T object) {
        objectToFilePretty(object, PAGE_AFTER_VOTING_JSON);
    }

    protected Member getMember(Element element) {
        Element pollResultAnswer = element.select("span.unicredit_poll_results_answer>:not(a[href])").first();
        if (pollResultAnswer == null) return null;

        String pollResultsCount = element.select("span.unicredit_poll_results_count").text();

        String count = substringBeforeSpaceByRegex(pollResultsCount);
        String percent = substringAfterSpaceByRegex(pollResultsCount);

        Member member = new Member();
        member.setId(id++);
        member.setInput("inp" + member.getId());
        member.setTitle(removeUTF8BOM(pollResultAnswer.ownText()));
        member.setCount(count);
        member.setPercent(percent);

        return member;
    }
}
