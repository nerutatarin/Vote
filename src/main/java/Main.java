import vote.browsers.*;
import vote.vote2022.kp.VoteKP;

import java.util.List;

import static io.github.bonigarcia.wdm.WebDriverManager.firefoxdriver;
import static java.util.Arrays.asList;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {

            int count = 1;

            List<Browsers> browsers = asList(new Chromium());
            browsers.forEach(browser -> new VoteKP(browser, count).start());

            /*VoteImpl kp = new VoteKP(new MsEdge());
            kp.start();*/
        }
    }
}