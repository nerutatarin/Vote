package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.configurations.MemberConfig;
import service.configurations.VoteConfig;
import service.pagemanager.model.Member;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.VotingPage;
import utils.Utils;
import votes.MemberRank;
import votes.MemberRanks;

import java.util.Date;
import java.util.List;

import static java.lang.Math.subtractExact;
import static java.util.Collections.singleton;
import static service.telegrambot.commands.CommandsEnum.COMMAND_RESULT;
import static utils.Thesaurus.FilesNameJson.MEMBER_RANKS_JSON;
import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.Thesaurus.FilesNameYaml.MEMBER_CONFIG_YAML;
import static utils.Thesaurus.FilesNameYaml.VOTE_CONFIG_YAML;
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

    private StringBuilder getResultDefault() {
        StringBuilder stringBuilder = new StringBuilder();

        MemberConfig memberConfig = new MemberConfig().parse();
        if (memberConfig == null) {
            log.error("Не найден файл: " + MEMBER_CONFIG_YAML);
            return null;
        }

        VoteConfig voteConfig = new VoteConfig().parse();
        if (voteConfig == null) {
            log.error("Не найден файл: " + VOTE_CONFIG_YAML);
            return null;
        }

        MemberRanks memberRanks = fileToObject(MEMBER_RANKS_JSON, MemberRanks.class);
        if (memberRanks == null) {
            log.error("Не найден файл: " + MEMBER_RANKS_JSON);
            return null;
        }

        for (MemberRank memberRank : memberRanks.getMemberRanks()) {
            if (memberRank.getRank() == 1) {
                int diffCount = subtractExact(memberRank.getCount(), memberRank.getCompetitorCount());
                stringBuilder
                        .append(memberRanks.getTimeStamp())
                        .append("\n")
                        .append("Участник: ")
                        .append("\n")
                        .append(memberRank.getMember())
                        .append("\n")
                        .append("занимает ")
                        .append(memberRank.getRank())
                        .append("-е место с ")
                        .append("```" + memberRank.getCount() + "```")
                        .append(" голосов, опережая конкурента ")
                        .append("\n")
                        .append(memberRank.getCompetitor())
                        .append("\n")
                        .append("на ")
                        .append(diffCount)
                        .append(" голосов ")
                        .append("\n")
                        .append("\n");

            } else {
                int diffCount = subtractExact(memberRank.getCompetitorCount(), memberRank.getCount());
                stringBuilder
                        .append(memberRanks.getTimeStamp())
                        .append("\n")
                        .append("Участник: ")
                        .append("\n")
                        .append("__" + memberRank.getMember() + "__")
                        .append("\n")
                        .append("занимает ")
                        .append("```" + memberRank.getRank() + "```")
                        .append("-е место с ")
                        .append(memberRank.getCount())
                        .append(" голосов, отставая от конкурента ")
                        .append("\n")
                        .append(memberRank.getCompetitor())
                        .append("\n")
                        .append(" на ")
                        .append(diffCount)
                        .append(" голосов ")
                        .append("\n")
                        .append("\n");
            }
        }
        return stringBuilder;
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
        if (resultVote == null || resultVote.getTitle() == null) {
            if (nullOrEmpty(singleton(stringBuilder))) {
                return stringBuilder.append("Искомый участник не найден!");
            }
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
