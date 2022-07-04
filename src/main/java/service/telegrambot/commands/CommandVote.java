package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.webdriver.browsers.Chrome;
import votes.kp.VoteKP;

import static java.lang.Thread.sleep;

public class CommandVote extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandVote.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        new VoteKP(new Chrome(), 1).start();

        sleep();

        return new StringBuilder().append("Проголосовано!");
    }

    private static void sleep() {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
