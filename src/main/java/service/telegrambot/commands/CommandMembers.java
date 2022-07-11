package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;

import java.util.List;

import static utils.Thesaurus.FilesNameJson.PAGE_BEFORE_VOTING_JSON;
import static utils.jackson.JsonMapper.fileToObject;

public class CommandMembers extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    protected Logger getLog() {
        return log;
    }

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        VotingPage votingPage = fileToObject(PAGE_BEFORE_VOTING_JSON, VotingPage.class);
        if (votingPage == null) return null;
        List<Member> memberList = votingPage.getMemberList();

        StringBuilder stringBuilder = new StringBuilder();
        memberList.forEach(member -> stringBuilder.append(member.getId()).append("-").append(member.getTitle()).append("\n"));
        return stringBuilder;
    }
}
