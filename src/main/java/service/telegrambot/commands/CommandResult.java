package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import service.memberRank.MemberRank;
import service.memberRank.MemberRanks;
import service.pagemanager.model.Member;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.VotingPage;
import utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static service.telegrambot.commands.CommandsEnum.COMMAND_RESULT;
import static utils.Thesaurus.FilesNameJson.MEMBER_RANKS_JSON;
import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.TimeUtils.PATTERN_DDMMYYYYHHMMSS;
import static utils.TimeUtils.formatDateWithPattern;
import static utils.Utils.*;
import static utils.jackson.JsonMapper.fileToObject;

public class CommandResult extends CommandsImpl {
    private static final Logger log = Logger.getLogger(CommandStatus.class);
    private Date timestamp;

    @Override
    protected StringBuilder replyMessageMake() {
        if (data.equals(COMMAND_RESULT.getValue())) return getResultDefault();

        String substringAfterSpace = firstSubstringAfterSpace(data);
        return getResultById(substringAfterSpace);
    }

    @Override
    protected Logger getLog() {
        return log;
    }

    protected StringBuilder getResultDefault() {
        StringBuilder stringBuilder = new StringBuilder();

        MemberRanks memberRanks = getMemberRanks();
        if (memberRanks == null) return null;

        int userRule = getUser().getRule();

        Set<String> memberTitles = getMemberTitles(userRule);

        Date memberRanksTimeStamp = memberRanks.getTimeStamp();
        for (MemberRank memberRank : memberRanks.getMemberRanks()) {
            if (memberTitles.contains(memberRank.getMember())) {
                stringBuilder.append("Результат голосования на ")
                        .append(formatDateWithPattern(memberRanksTimeStamp, PATTERN_DDMMYYYYHHMMSS))
                        .append("\n")
                        .append(memberRank);
            } else {
                stringBuilder.append("Результат голосования отсутствует, выполните команду /vote");
            }
        }

        return stringBuilder;
    }

    @Nullable
    private MemberRanks getMemberRanks() {
        MemberRanks memberRanks = fileToObject(MEMBER_RANKS_JSON, MemberRanks.class);
        if (memberRanks == null) {
            log.error("Не найден файл: " + MEMBER_RANKS_JSON);
            return null;
        }
        return memberRanks;
    }

    @NotNull
    private Set<String> getMemberTitles(int userRule) {
        List<service.configurations.Member> members = memberConfig.getMemberByRule(userRule);
        return members.stream().map(service.configurations.Member::getTitle).collect(toSet());
    }

    private StringBuilder getResultById(String substringAfterSpace) {
        VotingPage votingPage = fileToObject(PAGE_AFTER_VOTING_JSON, VotingPage.class);
        if (votingPage == null) return null;
        timestamp = votingPage.getTimeStamp();

        List<Member> memberList = votingPage.getMemberList();
        if (Utils.nullOrEmpty(memberList)) return null;

        ResultVote resultVote = new ResultVote();
        memberList.stream()
                .filter(participantVote -> isMemberId(substringAfterSpace, participantVote))
                .forEach(participantVote -> fillResultVote(resultVote, participantVote));


        StringBuilder stringBuilder = new StringBuilder();
        if (resultVote.getTitle() == null || nullOrEmpty(singleton(stringBuilder))) {
            return stringBuilder.append("Искомый участник не найден!");
        }

        return stringBuilder
                .append(timestamp)
                .append("\n")
                .append(resultVote.getTitle())
                .append("\n")
                .append(resultVote.getCount())
                .append("\n");
    }

    private void fillResultVote(ResultVote resultVote, Member member) {
        resultVote.setTitle(member.getTitle());
        resultVote.setCount(member.getCount());
    }

    private boolean isMemberId(String newData, Member member) {
        return member.getId() == parseInt(newData);
    }
}
