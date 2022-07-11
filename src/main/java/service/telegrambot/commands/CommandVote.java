package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.configurations.Member;

import java.util.List;

public class CommandVote extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandVote.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    private StringBuilder getStringBuilder() {

        if (!isAllowUser()) return new StringBuilder().append("Для Вас эта функция не доступна");

        int userRule = getUser().getRule();
        List<Member> members = memberConfig.getMemberByRule(userRule);

        List<Member> memberList = getMember(userRule, members);

        initVote(memberList);

        CommandResult commandResult = new CommandResult();
        return commandResult.getResultDefault();
    }
}
