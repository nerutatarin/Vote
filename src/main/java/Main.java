import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.configurations.BrowserConfig;
import service.configurations.MemberConfig;
import service.configurations.VoteConfig;
import service.configurations.VoteMode;
import service.telegrambot.TelegramBot;
import service.webdriver.Browsers;
import service.webdriver.browsers.Firefox;
import votes.KeepDistance;
import votes.ModelKeepDistance;
import votes.kp.VoteKP;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.sum;
import static java.lang.Math.subtractExact;
import static java.util.Arrays.asList;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        BrowserConfig browserConfig = new BrowserConfig().parse();
        if (browserConfig == null) {
            log.error("Конфиг браузеров не найден: browserConfig = " + browserConfig);
            return;
        }
        Map<String, service.configurations.Browsers> browsers = browserConfig.getBrowsers();

        MemberConfig memberConfig = new MemberConfig().parse();
        if (memberConfig == null) {
            log.error("Конфиг участников голосования не найден: memberConfig = " + memberConfig);
            return;
        }

        VoteConfig voteConfig = new VoteConfig().parse();
        if (voteConfig == null) {
            log.error("Конфиг голосования не найден: voteConfig = " + voteConfig);
            return;
        }
        VoteMode voteMode = voteConfig.getVoteMode();

        telegramBotInit();

        shuduledRun(memberConfig, voteConfig, voteMode);
    }

    private static void shuduledRun(MemberConfig memberConfig, VoteConfig voteConfig, VoteMode voteMode) {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("shuduledRun!");
                singleVoteInit(voteConfig.getVoteMode().getVoteCount());
                keepDistance(memberConfig, voteMode);
            }
        }, 0, 1, TimeUnit.HOURS);
        service.shutdown();
    }

    public static void singleVoteInit(int count) {
        new VoteKP(new Firefox(), count).start();
    }

    private static void telegramBotInit() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void keepDistance(MemberConfig memberConfig, VoteMode voteMode) {
        boolean keepDistanceEnabled = voteMode.isKeepDistanceEnabled();

        if (!keepDistanceEnabled) return;

        KeepDistance keepDistance = new KeepDistance(voteMode, memberConfig);
        List<ModelKeepDistance> keepDistances = keepDistance.init();

        for (ModelKeepDistance distance : keepDistances) {
            if (distance.getMemberRank() == 1) {
                int diffCount = subtractExact(distance.getMemberCount(), distance.getCompetitorCount());

                if (diffCount >= voteMode.getDistanceCount()) return;

                singleVoteInit(diffCount);
            } else {
                int diffCount = subtractExact(distance.getCompetitorCount(), distance.getMemberCount());
                int count = sum(diffCount, voteMode.getDistanceCount());

                singleVoteInit(count);
            }
        }
    }

    private static void threadVoteInit(int voteCount, int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            List<Browsers> browsers = asList(new Firefox());
            browsers.forEach(browser -> new VoteKP(browser, voteCount).start());
        }
    }
}