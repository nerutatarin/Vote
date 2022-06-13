package vote.pagemanager.model;

public class ResultsCount {
    private int id;
    private String title;
    private String count;
    private String percent;

    public String getInputId() {
        return "inp" + id;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "{" +
                "title='" + title + '\'' +
                ", count='" + count + '\'' +
                '}';
    }
}
