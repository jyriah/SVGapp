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
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.svgapp.view.MainView;
import org.svgapp.view.ShapeView;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        MainView svgMainView = new MainView();

        Scene scene = new Scene(svgMainView, 1000, 700);
        scene.getStylesheets().addAll("custom.css");

        primaryStage.setTitle("SVGapp :: Rakendus SVG vormingus graafika loomiseks");
        primaryStage.setScene(scene);
        primaryStage.show();

        Stage modalStage = new Stage();

        ShapeView shapeView = new ShapeView();

        Scene modalScene = new Scene(shapeView);
        modalStage.setScene(modalScene);
        modalStage.initOwner(primaryStage);
        modalStage.initModality(Modality.APPLICATION_MODAL);
        modalStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
