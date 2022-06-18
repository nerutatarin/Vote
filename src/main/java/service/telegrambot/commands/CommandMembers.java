package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.pagemanager.model.Member;

import java.util.List;

import static utils.jackson.JsonMapper.fileToListObject;

public class CommandMembers extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        String fileName = "participants";
        List<Member> members = fileToListObject(fileName, Member.class);

        StringBuilder stringBuilder = new StringBuilder();
        members.forEach(member -> stringBuilder.append(member.getId()).append("-").append(member.getTitle()).append("\n"));
        return stringBuilder;
    }
}
