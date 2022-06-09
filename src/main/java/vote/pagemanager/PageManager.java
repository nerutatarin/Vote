package vote.pagemanager;

import org.jsoup.nodes.Document;
import vote.pagemanager.model.VotePage;

import java.util.List;

public interface PageManager {

    void votePage(String baseUrl);

    List<VotePage> parseVotePage(Document pageSource);

    void voteInput();

    void voteButton();

    void voteLogging();

    void voteClose();
}
