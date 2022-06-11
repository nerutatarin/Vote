package vote.pagemanager.model;

public class Participant {
    private int id;
    private String input;
    private String titleMO;

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

    public String getTitleMO() {
        return titleMO;
    }

    public void setTitleMO(String titleMO) {
        this.titleMO = titleMO;
    }

    @Override
    public String toString() {
        return "Participant{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", titleMO='" + titleMO + '\'' +
                '}';
    }
}
