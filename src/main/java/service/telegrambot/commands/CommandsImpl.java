package service.telegrambot.commands;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.webdriver.browsers.Firefox;
import votes.kp.VoteKP;

public abstract class CommandsImpl implements Commands {

    protected String data;
    protected void singleThreadVoteInit() {
        new VoteKP(new Firefox(), 1).start();
    }

    @Override
    public SendMessage execute(String chatId, String data) {
        this.data = data;
        return sendMessage(chatId);
    }

    private SendMessage sendMessage(String chatId) {
        StringBuilder stringBuild = replyMessageMake();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(stringBuild.toString());

        if (stringBuild.length() > 0) stringBuild.setLength(0);
        return sendMessage;
    }

    protected abstract StringBuilder replyMessageMake();

}
