package service.pagemanager.parserpage;

import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import service.pagemanager.model.Member;

import static java.lang.Integer.parseInt;
import static utils.Thesaurus.FilesNameJson.PAGE_BEFORE_VOTING_JSON;
import static utils.Utils.removeUTF8BOM;
import static utils.jackson.JsonMapper.objectToFilePretty;

/**
 * Парсер страницы до голосования
 */

public class ParserPageBeforeVoting extends ParserPageImpl {

    @NotNull
    protected Elements getElements(Element questionData) {
        return questionData.getElementsByTag("label");
    }

    @Override
    protected <T> void savePageVote(T object) {
        objectToFilePretty(object, PAGE_BEFORE_VOTING_JSON);
    }

    protected Member getMember(Element element) {
        String participant = element.ownText();
        String inputId = element.attr("for");

        Member member = new Member();
        member.setId(parseInt(inputId.substring(3)));
        member.setInput(inputId);
        member.setTitle(removeUTF8BOM(participant));

        return member;
    }
}
