package vote;

import org.apache.log4j.Logger;
import vote.browsers.Browsers;
import vote.pagemanager.PageManager;

import java.util.ArrayList;
import java.util.List;

import static org.apache.log4j.Logger.getLogger;

public abstract class VoteImpl extends Thread implements Vote {
    private static final Logger log = getLogger(VoteImpl.class);
    protected String myIpUrl = "https://myip.ru/";
    protected PageManager pageManager;
    protected List<Browsers> browsers = new ArrayList<>();
    protected Browsers browser;

    @Override
    public void run() {
        log.info("Начало работы...");
        for (int i = 0; i < getVoteCount(); i++) {
            try {
                init();
            } catch (Exception e) {
                log.error("Ошибка: " + e);
            } finally {
                pageManager.voteClose();
            }
        }
    }

    protected abstract int getVoteCount();

    protected abstract String getMyIpUrl();

    protected abstract String getBaseUrl();
}
