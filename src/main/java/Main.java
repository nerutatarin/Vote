import vote.Vote;
import vote.vote2022.kp.VoteKP;
import vote.vote2022.orgzdrav.VoteOrgZdrav;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Vote kp = new VoteKP();
            kp.start();
            Vote orgZdrav = new VoteOrgZdrav();
            orgZdrav.start();
        }
    }
}