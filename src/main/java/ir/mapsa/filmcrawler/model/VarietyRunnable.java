package ir.mapsa.filmcrawler.model;

import ir.mapsa.filmcrawler.util.Constant;
import ir.mapsa.filmcrawler.util.MyDialog;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VarietyRunnable implements Runnable {

    private List<FilmNews> list;

    public VarietyRunnable() {
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        Document doc;
        try {
            doc = Jsoup.connect(Constant.VARIETY_URL).get();

            Elements articles = doc.getElementsByClass("o-tease lrv-u-flex");

            for (Element article : articles) {
                Element h3 = article.getElementsByTag("h3").get(0);
                String title = h3.getElementsByTag("a").text();
                String link = h3.getElementsByTag("a").attr("href");
                String imageLink = article.getElementsByTag("img").attr("data-lazy-src");

                FilmNews filmNews = new FilmNews();
                filmNews.setTitle(title);
                filmNews.setLink(link);
                filmNews.setImage(imageLink);

                list.add(filmNews);
                System.out.println(filmNews);
            }
        } catch (IOException e) {
            MyDialog.showErrorDialog("Connection", e.getMessage());
        }
    }

    public List<FilmNews> getList() {
        return list;
    }
}
