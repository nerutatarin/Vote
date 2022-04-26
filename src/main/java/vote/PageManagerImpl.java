package vote;

import java.util.ArrayList;

public interface PageManagerImpl {
    void startPage(String baseUrl);

    void chkVoteMo(ArrayList<String> inputs);

    void btnVote();

    void shutdown();

    void killProcess();
}
