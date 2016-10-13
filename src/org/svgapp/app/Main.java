package org.svgapp.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.svgapp.view.SVGMainView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        SVGMainView svgMainView = new SVGMainView();

        Scene scene = new Scene(svgMainView, 1000, 700);
        scene.getStylesheets().addAll("custom.css");

        primaryStage.setTitle("SVGapp | Rakendus SVG vormingus graafika loomiseks");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
