package vote;

import org.apache.log4j.Logger;
import vote.browsers.BrowsersImpl;
import vote.browsers.Firefox;
import vote.pagemanager.PageManagerImpl;

import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = getLogger(VoteImpl.class);
    protected String myIpUrl = "https://myip.ru/";
    protected PageManagerImpl pageManagerImpl;

    @Override
    public void run() {
        log.info("Начало работы...");
        for (int i = 0; i < getVoteCount(); i++) {
            try {
                init();
            } catch (Exception e) {
                log.error("Ошибка: " + e);
            } finally {
                pageManagerImpl.voteClose();
            }
        }
    }

    public void init() {
        List<BrowsersImpl> browsers = new ArrayList<>();
        browsers.add(new Firefox());
        //browsers.add(new EdgeBrowser());
        //browsers.add(new ChromeBrowser());
        //browsers.add(new ChromiumBrowser());
        //browsers.add(new OperaBrowser());
        //browsers.parallelStream().forEach(this::vote);
        browsers.forEach(this::vote);
    }

    protected abstract int getVoteCount();

    protected abstract String getMyIpUrl();

    protected abstract String getBaseUrl();
}
