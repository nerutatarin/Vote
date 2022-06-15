package service.pagemanager.model;

import java.util.Date;
import java.util.List;

public class PageVote {

    private Date timeStamp;
    private String nomination;
    private List<ParticipantVote> participantVote;

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }

    public List<ParticipantVote> getParticipant() {
        return participantVote;
    }

    public void setParticipant(List<ParticipantVote> participantVote) {
        this.participantVote = participantVote;
    }

    public String getNomination() {
        return nomination;
    }

    public void setNomination(String nomination) {
        this.nomination = nomination;
    }

    public List<ParticipantVote> getParticipantVote() {
        return participantVote;
    }

    public void setParticipantVote(List<ParticipantVote> participantVote) {
        this.participantVote = participantVote;
    }

    @Override
    public String toString() {
        return "PageVote{" +
                "timeStamp=" + timeStamp +
                ", nomination='" + nomination + '\'' +
                ", participantVote=" + participantVote +
                '}';
    }
}
