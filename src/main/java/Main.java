import vote.browsers.Browsers;
import vote.browsers.Chrome;
import vote.browsers.Firefox;
import vote.browsers.Opera;
import vote.vote2022.kp.VoteKP;

import java.util.List;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<Browsers> browsers = asList(new Firefox(), new Chrome(), new Opera());
            browsers.forEach(browser -> new VoteKP(browser).start());

            /*VoteImpl kp = new VoteKP(new MsEdge());
            kp.start();*/
        }
    }
}