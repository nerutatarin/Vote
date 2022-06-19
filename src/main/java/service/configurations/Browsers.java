package service.configurations;

public class Browsers {

    private boolean enable;

    private String key;

    private String name;

    private Options options;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

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
        return "Browsers{" +
                "isEnable=" + enable +
                ", key='" + key + '\'' +
                ", name='" + name + '\'' +
                ", options=" + options +
                '}';
    }
}
