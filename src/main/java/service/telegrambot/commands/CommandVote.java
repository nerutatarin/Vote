package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.webdriver.browsers.Firefox;
import votes.kp.VoteKP;

public class CommandVote extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandVote.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        new VoteKP(new Firefox(), 1).start();

        StringBuilder stringBuilder = new StringBuilder();
        return stringBuilder.append("Проголосовано!");
    }
}
