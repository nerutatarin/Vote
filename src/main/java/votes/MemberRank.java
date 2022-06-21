package votes;

public class MemberRank {
    private String member;
    private int rank;
    private int count;
    private String competitor;
    private int diff;
    private int competitorRank;
    private int competitorCount;

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getCompetitor() {
        return competitor;
    }

    public void setCompetitor(String competitor) {
        this.competitor = competitor;
    }

    public int getCompetitorCount() {
        return competitorCount;
    }

    public int getCompetitorRank() {
        return competitorRank;
    }

    public void setCompetitorRank(int competitorRank) {
        this.competitorRank = competitorRank;
    }

    public void setCompetitorCount(int competitorCount) {
        this.competitorCount = competitorCount;
    }

    @Override
    public String toString() {
        return "MemberRank{" +
                "member='" + member + '\'' +
                ", rank=" + rank +
                ", count=" + count +
                ", competitor='" + competitor + '\'' +
                ", diff=" + diff +
                ", competitorRank=" + competitorRank +
                ", competitorCount=" + competitorCount +
                '}';
    }
}
