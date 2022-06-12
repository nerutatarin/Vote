package utils.configurations.browsers;

public class BrowserType {

    private String key;

    private String name;

    private Options options;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "BrowserType{" +
                "key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
