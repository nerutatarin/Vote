package service.pagemanager.model;

public class ParticipantVote {
    private int id;
    private String input;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
