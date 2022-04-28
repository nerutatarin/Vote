import vote.VoteImpl;
import vote.vote2022.kp.VoteKP;
import vote.vote2022.orgzdrav.VoteOrgZdrav;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            VoteImpl kp = new VoteKP();
            kp.start();
            VoteImpl orgZdrav = new VoteOrgZdrav();
            orgZdrav.start();
        }
    }
}