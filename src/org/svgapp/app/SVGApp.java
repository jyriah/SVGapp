package org.svgapp.app;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.svgapp.view.SVGAppView;

public class SVGApp extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        SVGAppView svgAppView = new SVGAppView();

        Scene scene = new Scene(svgAppView, 1000, 700);
        scene.getStylesheets().addAll("custom.css");

        primaryStage.setScene(scene);
        primaryStage.setTitle("SVGapp :: Rakendus SVG vormingus graafika loomiseks");
        primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
