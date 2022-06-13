package votes.kp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import service.pagemanager.model.ResultsCount;
import utils.Utils;
import votes.kp.model.CookieKP;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Results {

    public List<ResultsCount> getResults() {
        WebDriver webDriver = WebDriverManager.firefoxdriver().create();
        try {
            webDriver.get("https://www.ufa.kp.ru/best/msk/oprosy/ufa_klinikagoda2022");
            Document pageSource = getPageSource(webDriver);
            if (pageSource == null) return null;

            Set<Cookie> cookies = getCookies(webDriver);
            //if (cookies.size() == 0) return null;

            //objectToFileWithGson(cookies);

            CookieKP[] cookieKPS = fileToObjectWithGson();

            addCookie(webDriver, cookieKPS);

            return getVoteCountList(pageSource);
        } catch (Exception e) {
            webDriver.quit();
        }
        return null;
    }

    private CookieKP[] fileToObjectWithGson() {
        String fileName = "src/resources/gson_cookie_kp.json";
        return Utils.fileToObjectWithGson(fileName, CookieKP[].class);
    }

    private void objectToFileWithObjectMapper(Set<Cookie> cookies) {
        String fileName = "src/resources/object_mapper_cookie_kp.json";
        Utils.objectToFileWithObjectMapper(cookies, fileName);
    }

    private void objectToFileWithGson(Set<Cookie> cookies) {
        String fileName = "src/resources/gson_cookie_kp.json";
        Utils.objectToFileWithGsonPretty(cookies, fileName);
    }

    private Set<Cookie> getCookies(WebDriver webDriver) {
        return webDriver.manage().getCookies();
    }

    private void addCookie(WebDriver webDriver, CookieKP[] cookieKPS) {
        Cookie cookie = null;

        for (CookieKP cookieKP : cookieKPS) {
            cookie = new Cookie(cookieKP.getName(),
                    cookieKP.getValue(),
                    cookieKP.getDomain(),
                    cookieKP.getPath(),
                    cookieKP.getExpiry(),
                    cookieKP.isSecure(),
                    cookieKP.isHttpOnly(),
                    cookieKP.getSameSite());
        }

        if (cookie == null) return;

        webDriver.manage().addCookie(cookie);
    }

    private Document getPageSource(WebDriver webDriver) {
        return Jsoup.parse(webDriver.getPageSource());
    }

    private List<ResultsCount> getVoteCountList(Document pageSource) {
        List<ResultsCount> resultsCountList = new ArrayList<>();
        Elements pollResultsAnswer = getPollResultsAnswer(pageSource);
        int id = 1;
        for (Element resultAnswer : pollResultsAnswer) {
            String pollResultAnswerTitle = resultAnswer.select("span.unicredit_poll_results_answer>:not(a[href])").first().ownText();
            String pollResultsCount = resultAnswer.getElementsByClass("unicredit_poll_results_count").text();
            String count = Utils.substringBeforeSpace(pollResultsCount);
            String percent = Utils.substringAfterSpace(pollResultsCount);

            ResultsCount resultsCount = new ResultsCount();
            resultsCount.setId(id++);
            resultsCount.setTitle(pollResultAnswerTitle);
            resultsCount.setCount(count);
            resultsCount.setPercent(percent);

            resultsCountList.add(resultsCount);
        }
        return resultsCountList;
    }

    private Elements getPollResultsAnswer(Document pageSource) {
        return pageSource.getElementsByClass("unicredit_poll_results_answer");
    }
}
