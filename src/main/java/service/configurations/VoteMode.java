package service.configurations;

import java.util.LinkedHashMap;
import java.util.Map;

public class VoteMode {

    private int voteCount;
    private int distanceCount;
    private boolean threadEnabled;
    private int threadCount;
    private boolean keepDistanceEnabled;
    private Map<String, Browsers> browsers = new LinkedHashMap<>();

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

    public Map<String, Browsers> getBrowsers() {
        return browsers;
    }

    public void setBrowsers(Map<String, Browsers> browsers) {
        this.browsers = browsers;
    }

    @Override
    public String toString() {
        return "VoteMode{" +
                "voteCount=" + voteCount +
                ", distanceCount=" + distanceCount +
                ", threadEnabled=" + threadEnabled +
                ", threadCount=" + threadCount +
                ", keepDistanceEnabled=" + keepDistanceEnabled +
                ", browsers=" + browsers +
                '}';
    }
}
