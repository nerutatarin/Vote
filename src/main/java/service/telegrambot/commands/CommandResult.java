package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import service.pagemanager.model.Member;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.VotingPage;
import utils.Utils;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.Utils.*;
import static utils.jackson.JsonMapper.fileToObject;

public class CommandResult extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private Date timestamp;

    @Override
    protected StringBuilder replyMessageMake() {
        return getStringBuilder();
    }

    private StringBuilder getStringBuilder() {
        String substringAfterSpace = firstSubstringAfterSpace(data);
        if (isBlankString(substringAfterSpace)) return null;

        ResultVote result = getResultVote(substringAfterSpace);
        if (result == null) {
            return new StringBuilder().append("Искомый участник не найден!");
        } else {
            return new StringBuilder()
                    .append(timestamp)
                    .append("\n")
                    .append(result.getTitle())
                    .append("\n")
                    .append(result.getCount())
                    .append("\n");
        }
    }

    private ResultVote getResultVote(String newData) {
        VotingPage votingPage = fileToObject(PAGE_AFTER_VOTING_JSON, VotingPage.class);
        if (votingPage == null) return null;
        timestamp = votingPage.getTimeStamp();

        Map<String, List<Member>> members = votingPage.getMembers();
        if (Utils.nullOrEmpty(members)) return null;

        List<Member> memberList = getMemberList(members);
        if (Utils.nullOrEmpty(memberList)) return null;

        ResultVote resultVote = new ResultVote();
        memberList.stream()
                .filter(participantVote -> isMemberId(newData, participantVote))
                .forEach(participantVote -> fillResultVote(resultVote, participantVote));

        return resultVote;
    }

    @NotNull
    private List<Member> getMemberList(Map<String, List<Member>> participantsMap) {
        return participantsMap.values()
                .stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private void fillResultVote(ResultVote resultVote, Member member) {
        resultVote.setTitle(member.getTitle());
        resultVote.setCount(member.getCount());
    }

    private boolean isMemberId(String newData, Member member) {
        return member.getId() == parseInt(newData);
    }
}
