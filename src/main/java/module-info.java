module ir.mapsa.filmcrawler {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens ir.mapsa.filmcrawler to javafx.fxml;
    exports ir.mapsa.filmcrawler;
    exports ir.mapsa.filmcrawler.controller;
    opens ir.mapsa.filmcrawler.controller to javafx.fxml;
}