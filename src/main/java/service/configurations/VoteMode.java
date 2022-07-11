package service.configurations;

public class VoteMode {

    private int voteCount;
    private int distanceCount;
    private boolean threadEnabled;
    private int threadCount;
    private boolean keepDistanceEnabled;

    public int getVoteCount() {
        return voteCount;
    }

    public void setVoteCount(int voteCount) {
        this.voteCount = voteCount;
    }

    public int getDistanceCount() {
        return distanceCount;
    }

    public void setDistanceCount(int distanceCount) {
        this.distanceCount = distanceCount;
    }

    public boolean isThreadEnabled() {
        return threadEnabled;
    }

    public void setThreadEnabled(boolean threadEnabled) {
        this.threadEnabled = threadEnabled;
    }

    public int getThreadCount() {
        return threadCount;
    }

    public void setThreadCount(int threadCount) {
        this.threadCount = threadCount;
    }

    public boolean isKeepDistanceEnabled() {
        return keepDistanceEnabled;
    }

    public void setKeepDistanceEnabled(boolean keepDistanceEnabled) {
        this.keepDistanceEnabled = keepDistanceEnabled;
    }

    @Override
    public String toString() {
        return "VoteMode{" +
                "voteCount=" + voteCount +
                ", distanceCount=" + distanceCount +
                ", threadEnabled=" + threadEnabled +
                ", threadCount=" + threadCount +
                ", keepDistanceEnabled=" + keepDistanceEnabled +
                '}';
    }
}
