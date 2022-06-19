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

        if (result == null) {
            return testResult();
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

    private StringBuilder testResult() {
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
                return new StringBuilder()
                        .append("Участник: ")
                        .append("\n")
                        .append(distance.getMember())
                        .append("\n")
                        .append("занимает ")
                        .append(distance.getMemberRank())
                        .append("-е место с ")
                        .append(distance.getMemberCount())
                        .append(" голосов, опережая конкурента на")
                        .append(diffCount);

            } else {
                int diffCount = subtractExact(distance.getCompetitorCount(), distance.getMemberCount());
                return new StringBuilder()
                        .append("Участник: ")
                        .append("\n")
                        .append(distance.getMember())
                        .append("\n")
                        .append("занимает ")
                        .append(distance.getMemberRank())
                        .append("-е место с ")
                        .append(distance.getMemberCount())
                        .append(" голосов, опережая конкурента на")
                        .append(diffCount);
            }
        }
        return new StringBuilder()
                .append("Искомый участник не найден!");
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
