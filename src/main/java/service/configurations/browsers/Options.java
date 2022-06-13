package service.configurations.browsers;

public class Options {

    private boolean headless;

    private String logLevel;

    private String pageLoadStrategy;

    private int pageLoadTimeout;

    private int implicitWaitTimeout;

    private int scriptTimeout;

    private boolean acceptInsecureCerts;

    public boolean isHeadless() {
        return headless;
    }

    public void setHeadless(boolean headless) {
        this.headless = headless;
    }

    public String getLogLevel() {
        return logLevel.toUpperCase();
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getPageLoadStrategy() {
        return pageLoadStrategy.toUpperCase();
    }

    public void setPageLoadStrategy(String pageLoadStrategy) {
        this.pageLoadStrategy = pageLoadStrategy;
    }

    public int getPageLoadTimeout() {
        return pageLoadTimeout;
    }

    public void setPageLoadTimeout(int pageLoadTimeout) {
        this.pageLoadTimeout = pageLoadTimeout;
    }

    public int getImplicitWaitTimeout() {
        return implicitWaitTimeout;
    }

    public void setImplicitWaitTimeout(int implicitWaitTimeout) {
        this.implicitWaitTimeout = implicitWaitTimeout;
    }

    public int getScriptTimeout() {
        return scriptTimeout;
    }

    public void setScriptTimeout(int scriptTimeout) {
        this.scriptTimeout = scriptTimeout;
    }

    public boolean isAcceptInsecureCerts() {
        return acceptInsecureCerts;
    }

    public void setAcceptInsecureCerts(boolean acceptInsecureCerts) {
        this.acceptInsecureCerts = acceptInsecureCerts;
    }

    @Override
    public String toString() {
        return "Options{" +
                "headless=" + headless +
                ", logLevel='" + logLevel + '\'' +
                ", pageLoadStrategy='" + pageLoadStrategy + '\'' +
                ", pageLoadTimeout=" + pageLoadTimeout +
                ", implicitWaitTimeout=" + implicitWaitTimeout +
                ", scriptTimeout=" + scriptTimeout +
                ", acceptInsecureCerts=" + acceptInsecureCerts +
                '}';
    }
}
