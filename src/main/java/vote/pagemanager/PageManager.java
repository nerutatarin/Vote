package vote.pagemanager;

import java.util.ArrayList;

public interface PageManager {
    void startPage(String baseUrl);

    void chkVoteMo(ArrayList<String> inputs);

    void btnVote();

    void shutdown();

    void killProcess();
}
