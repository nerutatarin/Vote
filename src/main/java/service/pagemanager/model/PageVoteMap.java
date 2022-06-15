package service.pagemanager.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class PageVoteMap {

    private Date timeStamp;
    private Map<String, List<ParticipantVote>> participantsMap;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Map<String, List<ParticipantVote>> getParticipantsMap() {
        return participantsMap;
    }

    public void setParticipantsMap(Map<String, List<ParticipantVote>> participantsMap) {
        this.participantsMap = participantsMap;
    }

    @Override
    public String toString() {
        return "PageVoteMap{" +
                "timeStamp=" + timeStamp +
                ", participantsMap=" + participantsMap +
                '}';
    }
}
