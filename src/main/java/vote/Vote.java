package vote;

import org.apache.log4j.Logger;
import vote.browsers.Browsers;
import vote.browsers.FirefoxBrowser;

import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public abstract class Vote extends Thread implements VoteImpl {
    private static final Logger log = getLogger(Vote.class);
    protected PageManager pageManager;

    @Override
    public void run() {
        log.info("Начало работы...");
        for (int i = 0; i < getVoteCount(); i++) {
            try {
                init();
            } catch (Exception e) {
                log.debug("Ошибка: ", e);
            } finally {
                pageManager.shutdown();
            }
        }
    }

    public void init() {
        List<Browsers> browsers = new ArrayList<>();
        browsers.add(new FirefoxBrowser());
        //browsers.add(new EdgeBrowser());
        //browsers.add(new ChromeBrowser());
        //browsers.add(new ChromiumBrowser());
        //browsers.add(new OperaBrowser());
        //browsers.parallelStream().forEach(this::vote);
        browsers.forEach(this::vote);
    }

    protected abstract int getVoteCount();

    protected abstract String getIpAddress();

    protected abstract String getMyIpUrl();

    protected abstract String getCssSelector();

    protected abstract String getBaseUrl();

    protected abstract ArrayList<String> getInputs();
}
