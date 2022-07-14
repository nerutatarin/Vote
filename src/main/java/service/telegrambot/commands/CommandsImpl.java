package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import service.configurations.*;
import service.webdriver.Browser;
import votes.kp.VoteKP;

import java.util.List;

import static java.util.stream.Collectors.toList;

public abstract class CommandsImpl implements Commands {

    protected String data;
    protected String chatId;
    protected TelegramUsers telegramUsers;
    protected MemberConfig memberConfig;
    protected VoteConfig voteConfig;

    @Override
    public SendMessage execute(String chatId, String data) {
        this.data = data;
        this.chatId = chatId;
        this.telegramUsers = new TelegramUsers().parse();
        this.memberConfig = new MemberConfig().parse();
        this.voteConfig = new VoteConfig().parse();
        return sendMessage(chatId);
    }

    private SendMessage sendMessage(String chatId) {
        StringBuilder replyMessage = replyMessageMake();

        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(validReplyMessage(replyMessage));

        if (replyMessage.length() > 0) replyMessage.setLength(0);
        return sendMessage;
    }

    private String validReplyMessage(StringBuilder replyMessage) {
        return replyMessage == null ? getErrorMessage() : replyMessage.toString();
    }

    protected abstract StringBuilder replyMessageMake();

    private String getErrorMessage() {
        return "На сервере произошла ошибка!";
    }

    protected abstract Logger getLog();

    protected boolean isAllowUser() {
        return telegramUsers.isAllowUser(chatId);
    }

    protected TelegramUser getUser() {
        return telegramUsers.getTelegramUsers()
                .stream()
                .filter(user -> chatId.equals(user.getTelegramId()))
                .findFirst()
                .orElse(null);
    }

    protected List<Member> getMember(int userRule, List<Member> members) {
        return members.stream()
                .filter(member -> member.getRuleSet().contains(userRule))
                .collect(toList());
    }

    protected void initVote(List<Member> members) {
        int voteCount = getVoteMode().getVoteCount();
        boolean isThread = getVoteMode().isThreadEnabled();
        List<Browser> browsers = voteConfig.getBrowsersInstance();
        new VoteKP(browsers, members).vote(voteCount, isThread);
    }

    private VoteMode getVoteMode() {
        return voteConfig.getVoteMode();
    }
}
