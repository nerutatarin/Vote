package votes.kp;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.FirefoxDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import service.pagemanager.model.ResultVote;
import utils.Utils;
import utils.jackson.JsonMapper;
import votes.kp.model.CookieKP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Results {

    public List<ResultVote> getResults() {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("moz:headless", false);

        WebDriverManager webDriverManager = new FirefoxDriverManager();
        webDriverManager.capabilities(capabilities);
        WebDriver webDriver = webDriverManager.create();

        try {
            //CookieKP[] cookieKPS = fileToObjectWithGson();
            //addCookie(webDriver, cookieKPS);

            webDriver.get("https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022");
            Document pageSource = getPageSource(webDriver);
            if (pageSource == null) return null;

            Set<Cookie> cookies = getCookies(webDriver);
            System.out.println(cookies);

            return getVoteCountList(pageSource);
        } catch (Exception e) {
            webDriver.quit();
        }
        return null;
    }

    private CookieKP[] fileToObjectWithGson() {
        String fileName = "src/resources/cookie_before_vote.json";
        return Utils.fileToObjectWithGson(fileName, CookieKP[].class);
    }

    private void objectToFileWithObjectMapper(Set<Cookie> cookies) {
        String fileName = "src/resources/object_mapper_cookie_kp.json";
        JsonMapper.objectToFile(cookies, fileName);
    }

    private void objectToFileWithGson(Set<Cookie> cookies) {
        String fileName = "src/resources/gson_cookie_kp.json";
        Utils.objectToFileWithGsonPretty(cookies, fileName);
    }

    private Set<Cookie> getCookies(WebDriver webDriver) {
        return webDriver.manage().getCookies();
    }

    private void addCookie(WebDriver webDriver, CookieKP[] cookieKPS) {
        if (cookieKPS == null) return;

        for (CookieKP cookieKP : cookieKPS) {
            Cookie cookie = new Cookie(cookieKP.getName(),
                    cookieKP.getValue(),
                    cookieKP.getDomain(),
                    cookieKP.getPath(),
                    cookieKP.getExpiry(),
                    cookieKP.isSecure(),
                    cookieKP.isHttpOnly(),
                    cookieKP.getSameSite());

            webDriver.manage().addCookie(cookie);
        }
    }

    private Document getPageSource(WebDriver webDriver) {
        return Jsoup.parse(webDriver.getPageSource());
    }

    private List<ResultVote> getVoteCountList(Document pageSource) {
        List<ResultVote> resultVoteList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            String pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpaceByRegex(pollResultsCount);
            String percent = Utils.substringAfterSpaceByRegex(pollResultsCount);

            ResultVote resultVote = new ResultVote();
            resultVote.setId(id++);
            resultVote.setTitle(pollResultAnswerTitle);
            resultVote.setCount(count);
            resultVote.setPercent(percent);

            resultVoteList.add(resultVote);
        }
        return resultVoteList;
    }

    private Elements getPollResultsAnswer(Document pageSource) {
        return pageSource.getElementsByClass("unicredit_poll_results_answer");
    }
}
