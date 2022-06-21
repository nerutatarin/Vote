package service.configurations;

import utils.yaml.YamlParser;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toMap;
import static utils.Thesaurus.FilesNameYaml.MEMBER_CONFIG_YAML;

public class MemberConfig {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public Map<String, String> getAllowMembers() {
        return getMembers()
                .stream()
                .filter(Member::getAllow)
                .collect(toMap(Member::getNomination, Member::getTitle, (a, b) -> b, LinkedHashMap::new));
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public MemberConfig parse() {
        return YamlParser.parse(getClass(), MEMBER_CONFIG_YAML);
    }

    @Override
    public String toString() {
        return "MemberConfig{" +
                "members=" + members +
                '}';
    }

    public static class Member {

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
            return "Member{" +
                    "title='" + title + '\'' +
                    ", nomination='" + nomination + '\'' +
                    ", allow=" + allow +
                    '}';
        }

    }
}
