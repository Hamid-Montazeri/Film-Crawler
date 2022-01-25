package ir.mapsa.filmcrawler.model;

public class FilmNews {

    private String title;
    private String link;
    private String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "FilmNews{" +
                "title='" + title + '\'' +
                ", link='" + link + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
