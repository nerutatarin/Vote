import vote.vote2022.Vote;
import vote.vote2022.VoteKP;

public class Main {
    public static void main(String[] args) {
        for (int i = 0; i < 1; i++) {
            Vote thread = new VoteKP();
            thread.start();
        }
    }

    public String setPropertyDependsOnOS() {
        if (System.getProperty("os.name").toLowerCase().contains("mac")) {
            return System.setProperty("webdriver.chrome.driver", "driverFolder/chromedriver");
        } else {
            return System.setProperty("webdriver.chrome.driver", "driverFolder/chromedriverLinux");
        }
    }
}