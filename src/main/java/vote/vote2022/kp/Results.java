package vote.vote2022.kp;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import utils.Utils;
import vote.pagemanager.model.ResultsCount;

import java.io.IOException;
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
            if (cookies.size() == 0) return null;

            objectToFileWithGson(cookies);

            //addCookie(webDriver);

            return getVoteCountList(pageSource);
        } catch (Exception e) {
            webDriver.quit();
        }
        return null;
    }

    private void convertJsonToObject(Set<Cookie> cookies) throws IOException {
        //String jsonMAPPER = getJson(cookies, mapper);

        //SetCookieKP cookieKPSet = mapper.readValue(jsonMAPPER, SetCookieKP.class);
        System.out.println();

        //String jsonInString = mapper.writeValueAsString(setCookieKP);


        //ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        //String json = ow.writeValueAsString(cookies);
        //ow.writeValue(new File("src/resources/cookie_kp.json"), json);
    }

    private void objectToFileWithObjectMapper(Set<Cookie> cookies) {
        String fileName = "src/resources/object_mapper_cookie_kp.json";
        Utils.objectToFileWithObjectMapper(cookies, fileName);
    }

    private void objectToFileWithGson(Set<Cookie> cookies) {
        String fileName = "src/resources/gson_cookie_kp.json";
        Utils.objectToFileWithGsonPretty(cookies, fileName);
    }

    private String getJson(Set<Cookie> cookies, ObjectMapper mapper) throws JsonProcessingException {
        String json = mapper.writeValueAsString(cookies);
        System.out.println(json);
        return json;
    }

    private Set<Cookie> getCookies(WebDriver webDriver) {
        return webDriver.manage().getCookies();
    }

    private void addCookie(WebDriver webDriver) {
        Cookie cookie1 = new Cookie("w3t", "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJzaWQiOiI2MTM3ODY4OC1jOTc1LTQxMDgtYWQ2NC1iMDIxMjhkMmUyNWEiLCJqdGkiOiI2YTRjNDIxZC1lMmNhLTQ5ZDAtYmMwMC1jOTc2YTFkZGU4NjkiLCJfdmVyc2lvbiI6MSwiX3BhdGgiOiIvIiwiX3RyYWNlIjoiODVhZDE2NDRmMGNkNDE5MTZhM2UwZDFkYzRhM2RmYzciLCJfcGF5bG9hZHMiOnsiZ2VvIjp7ImNvZGVyIjp7InN0cl9yZWdpb24iOiIiLCJyZWdpb24iOjMyLCJ1cGRhdGVkIjoiMjAyMi0wNi0xMFQxMzoyNDoxOVoiLCJzb3VyY2UiOiJnZW9jb2RlciJ9fSwicHJvZmlsZSI6bnVsbH0sIl9ncmFudHMiOm51bGwsImlzcyI6eyJlc3NlbnRpYWwiOmZhbHNlLCJ2YWx1ZXMiOlsid3d3LnVmYS5rcC5ydSJdfSwiZXhwIjoxNjU1NDcyMjU5LCJpYXQiOjE2NTQ4Njc0NTksIm5iZiI6MTY1NDg2NzQ1OSwic3ViIjoic2Vzc2lvbiJ9.ZaynSMnazcLYvKhHdb5FLOyYGbfo2GuGdd7HU_yPLCYdx4cMRAVuh05H8RD5x0abn2iMQ7zkGI9xDIfgJ1a3WyMnRVv40_d4UVocIZXObOQpOu3NVwN4DI-1cubmwiOidyHowYw28uFLdStV8rYeYOnilALxZJN9kfrQ4ly8-MWQjef3vzeJx7iPoQm48H-pETucnxWMUCe-_aQ0pE49Z1vRJA7SGEcYWN9UjLm_KCufs5dYrQhyG8QuFkTtm1l8sSiJUHM9PvVfHpPdbCy7iZlDFhGmv27BNiJUCV1TOzxpbcCbsEqkh210Mz_XB4sbPKUSukC-wZmy8pvpJN90Rg");
        Cookie cookie2 = new Cookie("w3k", "61378688-c975-4108-ad64-b02128d2e25a");
        webDriver.manage().addCookie(cookie1);
        webDriver.manage().addCookie(cookie2);
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
