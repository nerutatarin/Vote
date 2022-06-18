package service.pagemanager.model;

public class Member {
    private int id;
    private String input;
    private String title;
    private String count;
    private String percent;

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

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public String getInputById(){
        return "inp" + id;
    }

    @Override
    public String toString() {
        return "Member{" +
                "id=" + id +
                ", input='" + input + '\'' +
                ", title='" + title + '\'' +
                ", count='" + count + '\'' +
                ", percent='" + percent + '\'' +
                '}';
    }
}
