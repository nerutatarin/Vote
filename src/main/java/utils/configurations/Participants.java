package utils.configurations;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.util.List;

public class Participants {

    private List<Participant> participants;

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public Participants yamlParser() {
        InputStream is = getClass().getClassLoader().getResourceAsStream("participants.yaml");
        Yaml yaml = new Yaml(new Constructor(Participants.class));
        return yaml.load(is);
    }

    public static class Participant {
        private String title;
        private String nomination;
        private Boolean allow;

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

        public Boolean getAllow() {
            return allow;
        }

        public void setAllow(Boolean allow) {
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
