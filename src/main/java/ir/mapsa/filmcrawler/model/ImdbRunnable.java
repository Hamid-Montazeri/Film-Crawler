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

public class ImdbRunnable implements Runnable {

    private List<FilmNews> list;

    public ImdbRunnable() {
        list = new ArrayList<>();
    }

    @Override
    public void run() {
        Document doc;
        try {
            doc = Jsoup.connect(Constant.IMDB_URL).get();

            Elements articles = doc.getElementsByTag("article");

            for (Element article : articles) {
                Element header = article.getElementsByTag("header").get(0);
                Element a = header.getElementsByTag("a").get(0);
                String title = a.text();
                String link = a.attr("href");
                Element section = article.getElementsByTag("section").get(0);
                String imageLink = section.getElementsByTag("img").attr("src");

                FilmNews filmNews = new FilmNews();
                filmNews.setTitle(title);
                filmNews.setLink(link);
                filmNews.setImage(imageLink);

                list.add(filmNews);
//                System.out.println(filmNews);
            }
        } catch (IOException e) {
            MyDialog.showErrorDialog("Connection", e.getMessage());
        }
    }

    public List<FilmNews> getList() {
        return list;
    }

}

