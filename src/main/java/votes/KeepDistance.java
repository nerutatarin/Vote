package votes;

import org.apache.log4j.Logger;
import service.configurations.MemberConfig;
import service.configurations.VoteMode;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;

import java.util.*;

import static java.lang.Math.subtractExact;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toMap;
import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.jackson.JsonMapper.fileToObject;

public class KeepDistance {
    private static final Logger log = Logger.getLogger(KeepDistance.class);

    private final VoteMode voteCount;
    private VotingPage votingPage;
    private MemberConfig memberConfig;
    private Date timeStamp;

    public KeepDistance(VoteMode voteCount, MemberConfig memberConfig) {
        this.voteCount = voteCount;
        this.memberConfig = memberConfig;
    }

    public List<ModelKeepDistance> init() {
        Map<String, String> allowMembers = memberConfig.getAllowMembers();

        this.votingPage = getVotingPage();
        if (votingPage == null) return null;
        Map<String, List<Member>> members = votingPage.getMembers();
        timeStamp = votingPage.getTimeStamp();

        Map<String, List<Member>> sortedMembers = getSortedMemberList(members, allowMembers);

        return keepDistance(allowMembers, sortedMembers);
    }

    private VotingPage getVotingPage() {
        return fileToObject(PAGE_AFTER_VOTING_JSON, VotingPage.class);
    }

    private Map<String, List<Member>> getSortedMemberList(Map<String, List<Member>> members, Map<String, String> allowMembers) {
        return members.keySet().stream()
                .filter(allowMembers::containsKey)
                .collect(toMap(key -> key, key -> sortByCount(members.get(key)), (a, b) -> b, LinkedHashMap::new));
    }

    private List<Member> sortByCount(List<Member> memberList) {
        return memberList.stream()
                .sorted(comparingInt(Member::getCount).reversed())
                .collect(toList());
    }

    private List<ModelKeepDistance> keepDistance(Map<String, String> allowMembers, Map<String, List<Member>> sortedMembers) {
        List<ModelKeepDistance> modelKeepDistances = new ArrayList<>();;
        for (String s : sortedMembers.keySet()) {
            if (allowMembers.containsKey(s)) {
                List<Member> memberList = sortedMembers.get(s);;
                modelKeepDistances.add(determiningRank(allowMembers, memberList));
            }
        }
        return modelKeepDistances;
    }

    private ModelKeepDistance determiningRank(Map<String, String> allowMembers, List<Member> memberList) {
        ModelKeepDistance modelKeepDistance = new ModelKeepDistance();
        for (Member member : memberList) {
            String allowMemberTitle = member.getTitle();

            if (allowMembers.containsValue(allowMemberTitle)) {
                int allowMemberRank = memberList.indexOf(member);
                int allowMemberCount = memberList.get(allowMemberRank).getCount();

                modelKeepDistance.setTimeStamp(timeStamp);
                modelKeepDistance.setMember(allowMemberTitle);
                modelKeepDistance.setMemberRank(allowMemberRank + 1);
                modelKeepDistance.setMemberCount(allowMemberCount);

                if (allowMemberRank == 0) {
                    firstRank(memberList, allowMemberCount, modelKeepDistance);
                } else {
                    notFirstRank(memberList, allowMemberCount, modelKeepDistance);
                }
            }
        }
        return modelKeepDistance;
    }

    private void firstRank(List<Member> memberList, int allowMemberCount, ModelKeepDistance modelKeepDistance) {
        Member competitor = memberList.get(1);
        int competitorRank = memberList.indexOf(competitor) + 1;
        int diff = subtractExact(allowMemberCount, competitor.getCount());
        log.info("Наш участник на первом месте с " + allowMemberCount + " голосов, опережая конкурента на " + diff + " голосов");

        modelKeepDistance.setCompetitor(competitor.getTitle());
        modelKeepDistance.setCompetitorRank(competitorRank);
        modelKeepDistance.setCompetitorCount(competitor.getCount());

        log.info("Нет необходимости накручивать голоса!");
    }

    private void notFirstRank(List<Member> memberList, int allowMemberCount, ModelKeepDistance modelKeepDistance) {
        Member competitor = memberList.get(0);
        int competitorRank = memberList.indexOf(competitor);
        String competitorTitle = competitor.getTitle();
        int competitorCount = competitor.getCount();
        int diff = subtractExact(competitorCount, allowMemberCount);
        log.info("Конкурент " + competitorTitle + " с " + competitorCount + " голосов, впереди на " + diff + " голосов");

        modelKeepDistance.setCompetitor(competitorTitle);
        modelKeepDistance.setCompetitorRank(competitorRank + 1);
        modelKeepDistance.setCompetitorCount(competitorCount);
    }
}
