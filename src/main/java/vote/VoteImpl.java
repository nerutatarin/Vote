package vote;

public interface VoteImpl {
    void init(int voteCount);

    void writeToLog();

    void startPage(String url, String message);

    void chkVoteMo();

    void btnVote();


    void shutdown();
}
