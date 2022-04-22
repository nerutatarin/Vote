import vote.vote2022.Vote;
import vote.vote2022.VoteKP;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Vote thread = new VoteKP();
            thread.start();
        }
    }
}