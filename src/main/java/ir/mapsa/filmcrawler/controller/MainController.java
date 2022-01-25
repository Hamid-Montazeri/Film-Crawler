package ir.mapsa.filmcrawler.controller;

import ir.mapsa.filmcrawler.model.FilmNews;
import ir.mapsa.filmcrawler.model.ImdbRunnable;
import ir.mapsa.filmcrawler.model.VarietyRunnable;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class MainController {

    private HostServices hostServices;

    private List<FilmNews> imdbNews;
    private List<FilmNews> varietyNews;

    @FXML
    protected VBox parent;

    public MainController() {
        imdbNews = new ArrayList<>();
        varietyNews = new ArrayList<>();

        runImdbThread();
        runVarietyThread();
    }

    private void runImdbThread() {
        ImdbRunnable imdbRunnable = new ImdbRunnable();
        Thread imdbThread = new Thread(imdbRunnable, "IMDB Thread");
        imdbThread.start();

        imdbNews = imdbRunnable.getList();
    }

    private void runVarietyThread() {
        VarietyRunnable varietyRunnable = new VarietyRunnable();
        Thread varietyThread = new Thread(varietyRunnable, "Variety Thread");
        varietyThread.start();

        varietyNews = varietyRunnable.getList();

    }

    @FXML
    protected void showImdbNews() {
        setList(imdbNews);
    }

    @FXML
    protected void showVarietyNews() {
        setList(varietyNews);
    }

    private void setList(List<FilmNews> list) {
        if (parent.getChildren() != null) {
            parent.getChildren().clear();
        }

        ObservableList<FilmNews> items = FXCollections.observableArrayList(list);

        items.forEach(filmNews -> {

            if (filmNews != null) {
                String image = filmNews.getImage();
                if (Objects.equals(image, "")) {
                    image = "C:\\Users\\Hamid\\IdeaProjects\\Film Crawler\\src\\main\\resources\\raw\\placeholder.svg";
                }
                ImageView imageView = new ImageView(image);
                imageView.setFitHeight(64);
                imageView.setFitWidth(64);

                imageView.setOnMouseClicked(mouseEvent -> {
                    if (mouseEvent.getEventType() == MouseEvent.MOUSE_CLICKED) {
                        hostServices.showDocument(imageView.getImage().getUrl());
                    }
                });

                Label title = new Label(filmNews.getTitle());
                title.setFont(Font.font(title.getFont().getName(), FontWeight.BOLD, title.getFont().getSize()));
                Hyperlink hyperlink = new Hyperlink(filmNews.getLink());
                hyperlink.setOnAction(actionEvent -> {
                    hostServices.showDocument(hyperlink.getText());
                });

                HBox hBox = new HBox();
                hBox.setAlignment(Pos.CENTER_LEFT);
                hBox.setSpacing(20);
                hBox.setPadding(new Insets(8));

                VBox vBox = new VBox();
                vBox.setAlignment(Pos.TOP_LEFT);
                vBox.setSpacing(4);
                vBox.getChildren().addAll(title, hyperlink);

                hBox.getChildren().addAll(imageView, vBox);

                parent.getChildren().addAll(hBox);
            }

        });

    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }
}