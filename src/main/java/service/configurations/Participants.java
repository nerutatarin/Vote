package service.configurations;

import java.util.List;

import static utils.parsers.YamlParser.yamlParser;

public class Participants {

    private int numberOfVotes;

    private List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participants parse() {
        return yamlParser(getClass(), "participants.yaml");
    }

    public int getNumberOfVotes() {
        return numberOfVotes;
    }

    public void setNumberOfVotes(int numberOfVotes) {
        this.numberOfVotes = numberOfVotes;
    }

    @Override
    public String toString() {
        return "Participants{" +
                "numberOfVotes=" + numberOfVotes +
                ", participants=" + participants +
                '}';
    }

    public static class Participant {

        private String title;
        private String nomination;
        private boolean allow;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getNomination() {
            return nomination;
        }

        public void setNomination(String nomination) {
            this.nomination = nomination;
        }

        public boolean getAllow() {
            return allow;
        }

        public void setAllow(boolean allow) {
            this.allow = allow;
        }

        @Override
        public String toString() {
            return "Participant{" +
                    "title='" + title + '\'' +
                    ", nomination='" + nomination + '\'' +
                    ", allow=" + allow +
                    '}';
        }

    }
}
