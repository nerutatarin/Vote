package service.configurations;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static utils.Thesaurus.FilesNameYaml.MEMBER_CONFIG_YAML;

public class MemberConfig extends Config {

    private List<Member> members;

    public List<Member> getMembers() {
        return members;
    }

    public Map<String, String> getAllowMembers() {
        return getMembers().stream()
                .filter(Member::isAllow)
                .collect(toMap(Member::getNomination, Member::getTitle, (a, b) -> b, LinkedHashMap::new));
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getMemberByRule(int userRule) {
        return members.stream()
                .filter(member -> member.getRuleSet().contains(userRule))
                .collect(toList());
    }

    @Override
    public String toString() {
        return "MemberConfig{" +
                "members=" + members +
                '}';
    }

    @Override
    protected <T extends Config> Class<T> getConfigClass() {
        return (Class<T>) getClass();
    }

    @Override
    protected String getResource() {
        return MEMBER_CONFIG_YAML;
    }
}
