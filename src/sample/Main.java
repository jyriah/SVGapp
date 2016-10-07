package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Node center = null;
        // Node top = null;
        Node right = null;
        Node bottom = null;

        //Ülemine menüü
        Menu fileMenu = new Menu("File");
        Menu editMenu = new Menu("Edit");
        Menu typeMenu = new Menu("Type");
        Menu helpMenu = new Menu("Help");

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, typeMenu, helpMenu);

        HBox top = new HBox();
        top.getChildren().addAll(menuBar);

        // Vasak tööriistamenüü
        ToggleButton selectTool = new ToggleButton("S");
        selectTool.setPrefSize(40, 40);
        ToggleButton rectTool = new ToggleButton("T");
        rectTool.setPrefSize(40, 40);
        ToggleButton ovalTool = new ToggleButton("O");
        ovalTool.setPrefSize(40, 40);
        ToggleButton zoomTool = new ToggleButton("Z");
        zoomTool.setPrefSize(40, 40);

        ToggleGroup toolToggle = new ToggleGroup();
        toolToggle.getToggles().addAll(selectTool, rectTool, ovalTool, zoomTool);

        TilePane left = new TilePane(selectTool, rectTool, ovalTool, zoomTool);

        BorderPane root = new BorderPane(center, top, right, bottom, left);

        primaryStage.setTitle("SVGapp | Rakendus SVG vormingus graafika loomiseks");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
