package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.browsers.Firefox;
import votes.kp.VoteKP;

public abstract class CommandsImpl implements Commands {
    protected static final Logger log = Logger.getLogger(CommandsImpl.class);
    protected final StringBuilder builder;

    public CommandsImpl() {
        this.builder = new StringBuilder();
    }

    protected void singleThreadVoteInit() {
        new VoteKP(new Firefox(), 1).start();
    }


    protected void messageBuild(SendMessage message, Long userId) {
        log.info(builder.toString());
        message.setText(builder.toString());
        message.setChatId(String.valueOf(userId));
    }
}
