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
        if (rank == 1) {
            return buildMessage("опережая конкурента ");
        } else {
            return buildMessage("отставая от конкурента ");
        }
    }

    private String buildMessage(String text) {
        return new StringBuilder()
                .append("Участник: ")
                .append("\n")
                .append(member)
                .append("\n")
                .append("занимает ")
                .append(rank)
                .append("-е место с ")
                .append(count)
                .append(" голосов, ")
                .append(text)
                .append("\n")
                .append(competitor)
                .append("\n")
                .append("на ")
                .append(diff)
                .append(" голосов ")
                .append("\n")
                .append("\n")
                .toString();
    }
}
