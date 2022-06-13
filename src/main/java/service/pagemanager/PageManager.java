package service.pagemanager;

import org.jsoup.nodes.Document;
import service.pagemanager.model.PageVote;
import utils.ipaddress.model.IPAddress;

import java.util.List;

public interface PageManager {

    void votePage(String baseUrl);

    List<PageVote> parseVotePage(Document pageSource);

    void voteInput();

    void voteButton();

    void voteLogging(IPAddress IPAddress);
}
