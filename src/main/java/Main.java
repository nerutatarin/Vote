import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import service.browsers.Browsers;
import service.browsers.Firefox;
import service.telegrambot.TelegramBot;
import votes.kp.VoteKP;

import java.util.List;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        //telegramBotInit();
        singleThreadVoteInit();
    }

    private static void telegramBotInit() {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(new TelegramBot());
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    private static void singleThreadVoteInit() {
        new VoteKP(new Firefox(), 1).start();
    }

    private static void threadVoteInit(int thread) {
        for (int i = 0; i < thread; i++) {
            int count = 1;
            List<Browsers> browsers = asList(new Firefox());
            browsers.forEach(browser -> new VoteKP(browser, count).start());
        }
    }
}