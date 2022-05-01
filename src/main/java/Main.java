import vote.VoteImpl;
import vote.browsers.Browsers;
import vote.browsers.Chrome;
import vote.browsers.Edge;
import vote.browsers.Firefox;
import vote.vote2022.kp.VoteKP;
import vote.vote2022.orgzdrav.VoteOrgZdrav;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            /*List<Browsers> browsers = new ArrayList<>();
            browsers.add(new Firefox());
            browsers.add(new Edge());
            browsers.add(new Chrome());
            browsers.forEach(browser -> new VoteKP(browser).start());*/

            VoteImpl kp = new VoteKP(new Firefox());
            kp.start();

            VoteImpl orgZdrav = new VoteOrgZdrav(new Firefox());
            orgZdrav.start();
        }
    }
}