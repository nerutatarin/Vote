package service.configurations;

public class Browser {
    private boolean enable;
    private String name;
    private Driver driver;
    private Options options;

    public boolean isEnable() {
        return enable;
    }

    public void setEnable(boolean enable) {
        this.enable = enable;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public Options getOptions() {
        return options;
    }

    public void setOptions(Options options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "Browser{" +
                "enable=" + enable +
                ", name='" + name + '\'' +
                ", driver=" + driver +
                ", options=" + options +
                '}';
    }
}
