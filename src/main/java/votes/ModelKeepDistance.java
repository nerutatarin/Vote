package votes;

import java.util.Date;

public class ModelKeepDistance {
    private Date timeStamp;
    private String member;
    private int memberRank;
    private int memberCount;
    private String competitor;
    private int competitorRank;
    private int competitorCount;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMember() {
        return member;
    }

    public void setMember(String member) {
        this.member = member;
    }

    public int getMemberCount() {
        return memberCount;
    }

    public void setMemberCount(int memberCount) {
        this.memberCount = memberCount;
    }

    public int getMemberRank() {
        return memberRank;
    }

    public void setMemberRank(int memberRank) {
        this.memberRank = memberRank;
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
        return "ModelKeepDistance{" +
                "timeStamp=" + timeStamp +
                ", member='" + member + '\'' +
                ", memberRank=" + memberRank +
                ", memberCount=" + memberCount +
                ", competitor='" + competitor + '\'' +
                ", competitorRank=" + competitorRank +
                ", competitorCount=" + competitorCount +
                '}';
    }
}
