import vote.browsers.Browsers;
import vote.browsers.Firefox;
import vote.vote2022.kp.VoteKP;

import java.util.List;

import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {

            int count = 10000;
            boolean headless = true;
            boolean proxy = false;

            List<Browsers> browsers = asList(
                    new Firefox(headless, proxy)
                    //new Chromium(),
                    //new Opera(),
                    //new MsEdge()
            );

            browsers.forEach(browser -> new VoteKP(browser, count).start());
        }
    }
}