package example;

import org.apache.log4j.Logger;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import utils.configurations.Participants;
import utils.configurations.browsers.BrowserProperties;
import utils.configurations.browsers.BrowserType;

import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Config {
    private static final Logger log = Logger.getLogger(Config.class);

    public void yamlParser() {

        InputStream is = getClass().getClassLoader().getResourceAsStream("browser_properties.yaml");
        Yaml yaml = new Yaml(new Constructor(BrowserProperties.class));

        BrowserProperties browserProperties = yaml.load(is);
        Map<String, BrowserType> browserType = browserProperties.getBrowsersType();
        BrowserType firefox = browserType.get("opera");
        String driverName = firefox.getName();

        System.out.println();
    }

    public static void main(String[] args) {

        Participants participants = new Participants().yamlParser();
        List<Participants.Participant> participantList = participants.getParticipants();

        List<Participants.Participant> collect = participantList.stream()
                .filter(Participants.Participant::getAllow)
                .collect(Collectors.toList());

        System.out.println();
    }
}
