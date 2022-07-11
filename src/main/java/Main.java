import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.configurations.*;
import service.telegrambot.TelegramBot;
import service.webdriver.Browser;
import votes.MemberRank;
import votes.MemberRanks;
import votes.Ranker;
import votes.kp.VoteKP;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.lang.Integer.sum;
import static java.lang.Math.subtractExact;
import static java.util.concurrent.Executors.newSingleThreadScheduledExecutor;
import static java.util.concurrent.TimeUnit.HOURS;
import static utils.Utils.sleep;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class);
    private static BrowsersConfig browsersConfig;
    private static MemberConfig memberConfig;
    private static VoteConfig voteConfig;

    public static void main(String[] args) {
        initConfigs();
        initTelegramBot();

        //scheduledRun(memberConfig, voteConfig, voteMode);
    }

    private static void initConfigs() {
        browsersConfig = new BrowsersConfig().parse();
        memberConfig = new MemberConfig().parse();
        voteConfig = new VoteConfig().parse();
    }

    private static void initTelegramBot() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException("Запуск телеграмм бота завершился с ошибкой: " + e.getMessage());
        }
    }

    private static void scheduledRun() {
        TimeUnit hours = HOURS;
        newSingleThreadScheduledExecutor().scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                log.info("Планировщик запущен! Период = " + hours);
                singleVoteInit(1);
                sleep(10000);
                int count = keepDistance();
                singleVoteInit(count);
            }
        }, 0, 1, hours);
    }

    public static void singleVoteInit(int count) {
        boolean isThread = voteConfig.getVoteMode().isThreadEnabled();
        List<Browser> browsers = voteConfig.getBrowsersInstance();

        new VoteKP(browsers, count, memberConfig).vote(isThread);
    }

    private static int keepDistance() {
        VoteMode voteMode = voteConfig.getVoteMode();
        boolean keepDistanceEnabled = voteMode.isKeepDistanceEnabled();

        if (!keepDistanceEnabled) return 0;

        Ranker ranker = new Ranker(voteMode, memberConfig.getAllowMembers());
        MemberRanks memberRanks = ranker.init();

        for (MemberRank memberRank : memberRanks.getMemberRanks()) {
            if (memberRank.getRank() == 1) {
                int diffCount = subtractExact(memberRank.getCount(), memberRank.getCompetitorCount());

                if (diffCount >= voteMode.getDistanceCount()) return 0;

                int count = subtractExact(voteMode.getDistanceCount(), diffCount);
                log.info("Занимаем первое место, но дистанция меньше 2000, накручиваем " + count + " голосов");
                return count;
            } else {
                int diffCount = subtractExact(memberRank.getCompetitorCount(), memberRank.getCount());
                int count = sum(diffCount, voteMode.getDistanceCount());
                log.info("Занимаем " + memberRank.getRank() + " место с " + memberRank.getCount() + " голосов");
                return count;
            }
        }
        return 0;
    }

    private static void threadVoteInit(int voteCount, int threadCount) {
        for (int i = 0; i < threadCount; i++) {
            List<Member> members = memberConfig.getMembers();
            List<Browser> browsers = voteConfig.getBrowsersInstance();
            boolean isThread = voteConfig.getVoteMode().isThreadEnabled();

            browsers.forEach(browser -> new VoteKP(browsers, voteCount, members).vote(isThread));
        }
    }
}