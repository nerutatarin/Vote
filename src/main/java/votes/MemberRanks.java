package votes;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MemberRanks {
    private Date timeStamp;

    private List<MemberRank> memberRanks = new ArrayList<>();
    public MemberRanks() {
        this.timeStamp = new Date();
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<MemberRank> getMemberRanks() {
        return memberRanks;
    }

    public void setMemberRanks(List<MemberRank> memberRanks) {
        this.memberRanks = memberRanks;
    }

    public void setMemberRank(MemberRank memberRank) {
        this.memberRanks.add(memberRank);
    }

    @Override
    public String toString() {
        return "MemberRanks{" +
                "timeStamp=" + timeStamp +
                ", memberRanks=" + memberRanks +
                '}';
    }
}
