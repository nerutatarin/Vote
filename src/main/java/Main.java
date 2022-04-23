import vote.vote2022.Vote;
import vote.vote2022.Vote2022;
import vote.vote2022.browsers.Browsers;
import vote.vote2022.browsers.BrowsersImpl;
import vote.vote2022.browsers.ChromeBrowser;
import vote.vote2022.browsers.FirefoxBrowser;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            List<Browsers> browsers = new ArrayList<>();
            //browsers.add(new ChromeBrowser());
            browsers.add(new FirefoxBrowser());

            browsers.forEach(browser -> {
                Vote vote2022 = new Vote2022(browser);
                vote2022.start();
            });
        }
    }
}