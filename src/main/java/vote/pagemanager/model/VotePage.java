package vote.pagemanager.model;

import java.util.List;

public class VotePage {
    private String titleNomination;
    private List<Participant> participant;

    public List<Participant> getParticipant() {
        return participant;
    }

    public void setParticipant(List<Participant> participant) {
        this.participant = participant;
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
                ", participant=" + participant +
                '}';
    }
}
