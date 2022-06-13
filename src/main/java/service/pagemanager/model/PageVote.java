package service.pagemanager.model;

import java.util.List;

public class PageVote {
    private String titleNomination;
    private List<ParticipantVote> participantVote;

    public List<ParticipantVote> getParticipant() {
        return participantVote;
    }

    public void setParticipant(List<ParticipantVote> participantVote) {
        this.participantVote = participantVote;
    }

    public String getTitleNomination() {
        return titleNomination;
    }

    public void setTitleNomination(String titleNomination) {
        this.titleNomination = titleNomination;
    }

    @Override
    public String toString() {
        return "{" +
                "titleNomination='" + titleNomination + '\'' +
                ", participant=" + participantVote +
                '}';
    }
}
