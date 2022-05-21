import vote.VoteImpl;
import vote.browsers.Browsers;
import vote.browsers.Firefox;
import vote.browsers.MsEdge;
import vote.browsers.Opera;
import vote.vote2022.kp.VoteKP;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<Browsers> browsers = new ArrayList<>();
            browsers.add(new Firefox());
            browsers.add(new Opera());
            browsers.forEach(browser -> new VoteKP(browser).start());

            //VoteImpl kp = new VoteKP(new Firefox());
            //VoteImpl kp = new VoteKP(new Opera());
            //VoteImpl kp = new VoteKP(new MsEdge());
            //kp.start();

            /*VoteImpl orgZdrav = new VoteOrgZdrav(new Firefox());
            orgZdrav.start();*/
        }
    }
}