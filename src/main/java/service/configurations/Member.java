package service.configurations;

public class Member {
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
