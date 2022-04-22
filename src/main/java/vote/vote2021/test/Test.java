package vote.vote2021.test;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.Collections;
import java.util.Comparator;

public class Test {
    public static void main(String[] args) {
        String html = "<div class=\"questiondata\"> \n" +
                " <div class=\"question\"> <span class=\"noimg\">1. Лучшая многопрофильная клиника.</span> \n" +
                "  <div> \n" +
                "  </div> \n" +
                " </div> \n" +
                " <div class=\"unicredit_poll_results\"> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFF\uFEFFГБУЗ РБ ГКБ № 13 г. Уфа </span> <span class=\"unicredit_poll_results_line\" style=\"width: 25.047691720717%;\"></span> <span class=\"unicredit_poll_results_count\">6565 (25.05%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ Республиканская клиническая больница имени Г.Г.Куватова </span> <span class=\"unicredit_poll_results_line\" style=\"width: 4.4448683708508%;\"></span> <span class=\"unicredit_poll_results_count\">1165 (4.44%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Городская клиническая больница № 21 г. Уфа <a href=\"http://ufagkb21.ru/\" target=\"_blank\" class=\"button7\">Связаться с нами</a> <a href=\"https://www.instagram.com/gkb21_ufa/\" target=\"_blank\" class=\"button7\">Акции</a> <a href=\"https://www.ufa.kp.ru/daily/27243/4370707/?utm_campaign=adnative&amp;utm_medium=main_region&amp;utm_source=quote_preview&amp;utm_term=3\" target=\"_blank\" class=\"button7\">Подробнее</a> </span> <span class=\"unicredit_poll_results_line\" style=\"width: 52.033574971385%;\"></span> <span class=\"unicredit_poll_results_count\">13638 (52.03%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Больница скорой медицинской помощи г. Уфа </span> <span class=\"unicredit_poll_results_line\" style=\"width: 1.0873712323541%;\"></span> <span class=\"unicredit_poll_results_count\">285 (1.09%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Городская клиническая больница № 18 г. Уфа </span> <span class=\"unicredit_poll_results_line\" style=\"width: 6.4784433422358%;\"></span> <span class=\"unicredit_poll_results_count\">1698 (6.48%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFКлиника, БГМУ <a href=\"http://kbgmu.ru/\" target=\"_blank\" class=\"button7\">Связаться с нами</a> <a href=\"https://www.instagram.com/clinbsmu?igshid=13n8jv2f222o1/\" target=\"_blank\" class=\"button7\">Акции</a> <a href=\"https://www.ufa.kp.ru/daily/27257/4389264/\" target=\"_blank\" class=\"button7\">Подробнее</a> </span> <span class=\"unicredit_poll_results_line\" style=\"width: 3.1018695154521%;\"></span> <span class=\"unicredit_poll_results_count\">813 (3.10%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFМУП Хозрасчетная поликлиника г. Уфа </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.89278901182755%;\"></span> <span class=\"unicredit_poll_results_count\">234 (0.89%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFПроМедицина, медицинский холдинг </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.91949637542923%;\"></span> <span class=\"unicredit_poll_results_count\">241 (0.92%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Городская клиническая больница №8 г. Уфа <a href=\"http://gkb8-ufa.ru/\" target=\"_blank\" class=\"button7\">Связаться с нами</a> <a href=\"https://www.instagram.com/gkb8.ufa/\" target=\"_blank\" class=\"button7\">Акции</a> </span> <span class=\"unicredit_poll_results_line\" style=\"width: 5.0667684090042%;\"></span> <span class=\"unicredit_poll_results_count\">1328 (5.07%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Городская клиническая больница № 5 г. Уфа </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.92712705074399%;\"></span> <span class=\"unicredit_poll_results_count\">243 (0.93%)</span> </span> \n" +
                "  </div> \n" +
                " </div> \n" +
                "</div>\n" +
                "<div class=\"questiondata\"> \n" +
                " <div class=\"question\"> <span class=\"noimg\">2. Лучший специализированный медицинский центр </span> \n" +
                "  <div> \n" +
                "  </div> \n" +
                " </div> \n" +
                " <div class=\"unicredit_poll_results\"> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ Республиканская клиническая инфекционная больница <a href=\"http://gbuzrkib.ru/\" target=\"_blank\" class=\"button7\">Связаться с нами</a> <a href=\"http://gbuzrkib.ru/\" target=\"_blank\" class=\"button7\">Акции</a> <a href=\"https://www.ufa.kp.ru/daily/27256/4387458/\" target=\"_blank\" class=\"button7\">Подробнее</a> </span> <span class=\"unicredit_poll_results_line\" style=\"width: 53.889121939373%;\"></span> <span class=\"unicredit_poll_results_count\">24408 (53.89%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFМЗ РБ ГАУЗ Республиканский клинический онкологический диспансер </span> <span class=\"unicredit_poll_results_line\" style=\"width: 26.09674784183%;\"></span> <span class=\"unicredit_poll_results_count\">11820 (26.10%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFМЗ РБ ГБУЗ Республиканский кардиологический центр </span> <span class=\"unicredit_poll_results_line\" style=\"width: 14.671141235953%;\"></span> <span class=\"unicredit_poll_results_count\">6645 (14.67%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ Республиканский кожно-венерологический диспансер № 1 </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.32455346300753%;\"></span> <span class=\"unicredit_poll_results_count\">147 (0.32%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ РБ Республиканская клиническая психиатрическая больница </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.33559269644316%;\"></span> <span class=\"unicredit_poll_results_count\">152 (0.34%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFМЗ РБ ГБУЗ Республиканский клинический противотуберкулезный диспансер <a href=\" http://www.tubrb.ru/kontakt.html\" target=\"_blank\" class=\"button7\">Связаться с нами</a> <a href=\"http://www.tubrb.ru/index.php?option=com_content&amp;view=article&amp;id=840\" target=\"_blank\" class=\"button7\">Акции</a> <a href=\"https://www.ufa.kp.ru/daily/27263.5/4395709/\" target=\"_blank\" class=\"button7\">Подробнее</a> </span> <span class=\"unicredit_poll_results_line\" style=\"width: 4.6342701962776%;\"></span> <span class=\"unicredit_poll_results_count\">2099 (4.63%)</span> </span> \n" +
                "  </div> \n" +
                "  <div class=\"unicredit_poll_results_block\"> <span class=\"unicredit_poll_results_answer\"> <span>\uFEFFГБУЗ Республиканский клинический госпиталь ветеранов войн </span> <span class=\"unicredit_poll_results_line\" style=\"width: 0.048572627116773%;\"></span> <span class=\"unicredit_poll_results_count\">22 (0.05%)</span> </span> \n" +
                "  </div> \n" +
                " </div> \n" +
                "</div>";
        Document document = Jsoup.parse(html);
        Elements divData = document.getElementsByClass("questiondata");

        for (Element eDiv : divData) {
            Element span = eDiv.select("span").first();
            System.out.println(span.text());
            Elements parseElements = eDiv.select("div.unicredit_poll_results > div.unicredit_poll_results_block");
            for (Element eSpan : parseElements) {

                Element nameMo = eSpan.select("span.unicredit_poll_results_answer>:not(a[href])").first();
//                System.out.println(nameMo.ownText());
                Elements countVote = eSpan.select("span.unicredit_poll_results_count");
                System.out.println(nameMo.ownText() + " - " + countVote.text());
                Collections.sort(countVote, new Comparator<Element>() {
                    @Override
                    public int compare(Element o1, Element o2) {
                        return o1.text().compareTo(o2.text());
                    }
                });
                //System.out.println(countVote.text());


            }
        }

    }

    public static void test() {
        String html = "<h6 class='uiStreamMessage' data-ft=''><span class='messageBody' data-ft=''>Twisted<a href='http://'><span>http://</span><span class='word_break'></span>www.tb.net/</a> Balloons</span></h6>";
        Document doc = Jsoup.parse(html);
        Elements elements = doc.select("h6.uiStreamMessage > span.messageBody");
        for (Element e : elements) {
            System.out.println("All text:" + e.text());
            System.out.println("Only messageBody text:" + e.ownText());
        }
    }


}
