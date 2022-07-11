package votes;

import org.apache.log4j.Logger;
import service.configurations.VoteMode;
import service.pagemanager.model.Member;
import service.pagemanager.model.VotingPage;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import static java.lang.Math.subtractExact;
import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.toList;
import static utils.Thesaurus.FilesNameJson.MEMBER_RANKS_JSON;
import static utils.Thesaurus.FilesNameJson.PAGE_AFTER_VOTING_JSON;
import static utils.jackson.JsonMapper.fileToObject;
import static utils.jackson.JsonMapper.objectToFilePretty;

public class Ranker {
    private static final Logger log = Logger.getLogger(Ranker.class);

    private final VoteMode voteCount;
    private VotingPage votingPage;
    private  Map<String, String> allowMembers;
    private Date timeStamp;

    public Ranker(VoteMode voteCount,  Map<String, String> allowMembers) {
        this.voteCount = voteCount;
        this.allowMembers = allowMembers;
    }

    public MemberRanks init() {

        this.votingPage = getVotingPage();
        if (votingPage == null) return null;
        Map<String, List<Member>> members = votingPage.getMembers();
        timeStamp = votingPage.getTimeStamp();

        Map<String, List<Member>> sortedMembers = getSortedMemberList(members, allowMembers);

        MemberRanks memberRanks = keepDistance(allowMembers, sortedMembers);
        objectToFilePretty(memberRanks, MEMBER_RANKS_JSON);

        return memberRanks;
    }

    private VotingPage getVotingPage() {
        return fileToObject(PAGE_AFTER_VOTING_JSON, VotingPage.class);
    }

    private Map<String, List<Member>> getSortedMemberList(Map<String, List<Member>> members, Map<String, String> allowMembers) {
        LinkedHashMap<String, List<Member>> map = new LinkedHashMap<>();
        for (String key : members
                .keySet()) {
            if (allowMembers.containsKey(key)) {
                map.put(key, sortByCount(members.get(key)));
            }
        }
        return map;
    }

    private List<Member> sortByCount(List<Member> memberList) {
        return memberList.stream()
                .sorted(comparingInt(Member::getCount).reversed())
                .collect(toList());
    }

    private MemberRanks keepDistance(Map<String, String> allowMembers, Map<String, List<Member>> sortedMembers) {
        MemberRanks memberRanks = new MemberRanks();
        for (String s : sortedMembers.keySet()) {
            if (allowMembers.containsKey(s)) {
                List<Member> memberList = sortedMembers.get(s);
                MemberRank memberRank = getMemberRank(allowMembers, memberList);
                memberRanks.setMemberRank(memberRank);
            }
        }
        return memberRanks;
    }

    private MemberRank getMemberRank(Map<String, String> allowMembers, List<Member> memberList) {
        MemberRank memberRank = new MemberRank();
        memberList.stream()
                .filter(member -> allowMembers.containsValue(member.getTitle()))
                .forEach(member -> determiningRank(memberList, memberRank, member, member.getTitle()));
        return memberRank;
    }

    private void determiningRank(List<Member> memberList, MemberRank memberRank, Member member, String title) {
        int rank = memberList.indexOf(member);
        int count = memberList.get(rank).getCount();

        memberRank.setMember(title);
        memberRank.setRank(rank + 1);
        memberRank.setCount(count);

        Member competitor = getCompetitor(memberList, rank);
        int competitorRank = memberList.indexOf(competitor);
        memberRank.setCompetitor(competitor.getTitle());
        memberRank.setCompetitorRank(competitorRank + 1);
        memberRank.setCompetitorCount(competitor.getCount());

        int diff = subtractExact(count, competitor.getCount());
        memberRank.setDiff(diff);
    }

    private Member getCompetitor(List<Member> memberList, int rank) {
        return rank == 0 ? memberList.get(1) : memberList.get(0);
    }
}
