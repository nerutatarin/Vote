package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.browsers.Firefox;
import votes.kp.VoteKP;

public abstract class CommandsImpl implements Commands {

    protected SendMessage sendMessage = new SendMessage();

    public CommandsImpl() {}

    protected void singleThreadVoteInit() {
        new VoteKP(new Firefox(), 1).start();
    }
    
    protected SendMessage sendMessageBuild(Long userId, String stringMessage) {
        sendMessage.setChatId(String.valueOf(userId));
        sendMessage.setText(stringMessage);
        return sendMessage;
    }
}
