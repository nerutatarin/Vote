package service.telegrambot.commands;

import org.apache.log4j.Logger;
import service.configurations.MemberConfig;
import service.configurations.VoteConfig;
import service.configurations.VoteMode;
import service.pagemanager.model.Member;
import service.pagemanager.model.ResultVote;
import service.pagemanager.model.VotingPage;
import utils.Utils;
import votes.KeepDistance;
import votes.ModelKeepDistance;

import java.util.Date;
import java.util.List;

import static java.lang.Math.subtractExact;
import static java.util.Collections.singleton;
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
        ResultVote result = getResultVote();
        StringBuilder stringBuilder = new StringBuilder();

        if (result == null || result.getTitle() == null) {
            stringBuilder = testResult(stringBuilder);

            if (nullOrEmpty(singleton(stringBuilder))) {
                return new StringBuilder().append("Искомый участник не найден!");
            }

            return stringBuilder;
        } else {
            return stringBuilder
                    .append(timestamp)
                    .append("\n")
                    .append(result.getTitle())
                    .append("\n")
                    .append(result.getCount())
                    .append("\n");
        }
    }

    private StringBuilder testResult(StringBuilder stringBuilder) {
        MemberConfig memberConfig = new MemberConfig().parse();
        if (memberConfig == null) {
            log.error("Конфиг участников голосования не найден: memberConfig = " + memberConfig);
            return null;
        }

        VoteConfig voteConfig = new VoteConfig().parse();
        if (voteConfig == null) {
            log.error("Конфиг голосования не найден: voteConfig = " + voteConfig);
            return null;
        }
        VoteMode voteMode = voteConfig.getVoteMode();

        KeepDistance keepDistance = new KeepDistance(voteMode, memberConfig);
        List<ModelKeepDistance> keepDistances = keepDistance.init();

        for (ModelKeepDistance distance : keepDistances) {
            if (distance.getMemberRank() == 1) {
                int diffCount = subtractExact(distance.getMemberCount(), distance.getCompetitorCount());
                stringBuilder
                        .append(distance.getTimeStamp())
                        .append("\n")
                        .append("Участник: ")
                        .append("\n")
                        .append(distance.getMember())
                        .append("\n")
                        .append("занимает ")
                        .append(distance.getMemberRank())
                        .append("-е место с ")
                        .append(distance.getMemberCount())
                        .append(" голосов, опережая конкурента ")
                        .append("\n")
                        .append(distance.getCompetitor())
                        .append("\n")
                        .append("на ")
                        .append(diffCount)
                        .append(" голосов ")
                        .append("\n")
                        .append("\n");

            } else {
                int diffCount = subtractExact(distance.getCompetitorCount(), distance.getMemberCount());
                stringBuilder
                        .append(distance.getTimeStamp())
                        .append("\n")
                        .append("Участник: ")
                        .append("\n")
                        .append(distance.getMember())
                        .append("\n")
                        .append("занимает ")
                        .append(distance.getMemberRank())
                        .append("-е место с ")
                        .append(distance.getMemberCount())
                        .append(" голосов, отставая от конкурента ")
                        .append("\n")
                        .append(distance.getCompetitor())
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

    private ResultVote getResultVote() {
        String substringAfterSpace = firstSubstringAfterSpace(data);
        if (isBlankString(substringAfterSpace)) return null;

        VotingPage votingPage = fileToObject(PAGE_AFTER_VOTING_JSON, VotingPage.class);
        if (votingPage == null) return null;
        timestamp = votingPage.getTimeStamp();

        List<Member> memberList = votingPage.getMemberList();
        if (Utils.nullOrEmpty(memberList)) return null;

        ResultVote resultVote = new ResultVote();
        memberList.stream()
                .filter(participantVote -> isMemberId(substringAfterSpace, participantVote))
                .forEach(participantVote -> fillResultVote(resultVote, participantVote));

        return resultVote;
    }

    private void fillResultVote(ResultVote resultVote, Member member) {
        resultVote.setTitle(member.getTitle());
        resultVote.setCount(member.getCount());
    }

    private boolean isMemberId(String newData, Member member) {
        return member.getId() == parseInt(newData);
    }
}
