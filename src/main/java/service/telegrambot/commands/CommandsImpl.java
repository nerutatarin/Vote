package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.browsers.Firefox;
import utils.Utils;
import votes.kp.VoteKP;

public abstract class CommandsImpl implements Commands {

    protected SendMessage sendMessage = new SendMessage();
    protected final StringBuilder stringMessage = new StringBuilder();
    protected String message;

    protected String getMessage(String text) {
        return message = Utils.firstSubstringAfterSpace(text);
    }

    protected void singleThreadVoteInit() {
        new VoteKP(new Firefox(), 1).start();
    }

    protected SendMessage sendMessageBuild(Long userId) {
        sendMessage.setChatId(String.valueOf(userId));
        sendMessage.setText(stringMessage.toString());

        if (stringMessage.length() > 0) stringMessage.setLength(0);
        return sendMessage;
    }
}