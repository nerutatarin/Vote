package service.pagemanager.model;

import utils.Utils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class VotingPage {

    private Date timeStamp;
    private Map<String, List<Member>> members;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, List<Member>> getMembers() {
        return members;
    }

    public void setMembers(Map<String, List<Member>> members) {
        this.members = members;
    }

    public List<Member> getMemberList() {
        Map<String, List<Member>> members = getMembers();
        if (Utils.nullOrEmpty(members)) return null;

        return members.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    @Override
    public String toString() {
        return "VotingPage{" +
                "timeStamp=" + timeStamp +
                ", members=" + members +
                '}';
    }
}
