package service.configurations;

import java.util.List;

import static utils.Utils.toIntegerList;

public class Member {
    private String title;
    private String nomination;
    private boolean allow;
    private String rule;

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

    public boolean isAllow() {
        return allow;
    }

    public void setAllow(boolean allow) {
        this.allow = allow;
    }

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public List<Integer> getRuleSet() {
        return toIntegerList(rule);
    }

    @Override
    public String toString() {
        return "Member{" +
                "title='" + title + '\'' +
                ", nomination='" + nomination + '\'' +
                ", allow=" + allow +
                ", rule='" + rule + '\'' +
                '}';
    }
}
