package service.telegrambot.commands;

import org.apache.log4j.Logger;
import org.jetbrains.annotations.NotNull;
import service.configurations.Member;
import service.memberRank.MemberRank;
import service.memberRank.MemberRanks;
import utils.Utils;

import java.util.Date;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static utils.Thesaurus.FilesNameJson.MEMBER_RANKS_JSON;
import static utils.TimeUtils.PATTERN_DDMMYYYYHHMMSS;
import static utils.TimeUtils.formatDateWithPattern;
import static utils.jackson.JsonMapper.fileToObject;

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
        StringBuilder stringBuilder = new StringBuilder();

        if (!isAllowUser()) return stringBuilder.append("Для Вас эта функция не доступна");

        int userRule = getUser().getRule();

        List<Member> members = memberConfig.getMemberByRule(userRule);

        initVote(getMember(userRule, members));

        MemberRanks memberRanks = getMemberRanks();

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

    private MemberRanks getMemberRanks() {
        MemberRanks memberRanks = fileToObject(MEMBER_RANKS_JSON, MemberRanks.class);

        Utils.requireNonNull(memberRanks,"Не найден файл: " + MEMBER_RANKS_JSON);

        return memberRanks;
    }

    @NotNull
    private Set<String> getMemberTitles(int userRule) {
        List<service.configurations.Member> members = memberConfig.getMemberByRule(userRule);
        return members.stream().map(service.configurations.Member::getTitle).collect(toSet());
    }
}
