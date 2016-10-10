package org.svgapp.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;

/**
 * Klass, mis annab Stage klassile sisendiks õige Scene objekti
 */
public class ViewSVG {

    // Staatiline meetod, mis genereerib peamisele aknale Scene objekti
    public static Scene getPrimaryScene(int width, int height) {
        Node center = null;
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

        // Tooltipid tööriistanuppudele
        Tooltip selectTooltip = new Tooltip("Select tool");
        Tooltip rectTooltip = new Tooltip("Rectangle tool");
        Tooltip ovalTooltip = new Tooltip("Oval tool");
        Tooltip zoomTooltip = new Tooltip("Zoom tool");

        // Vasak tööriistamenüü
        ToggleButton selectTool = new ToggleButton("S");
        selectTool.setPrefSize(40, 40);
        selectTool.setTooltip(selectTooltip);

        ToggleButton rectTool = new ToggleButton("R");
        rectTool.setTooltip(rectTooltip);
        rectTool.setPrefSize(40, 40);

        ToggleButton ovalTool = new ToggleButton("O");
        ovalTool.setPrefSize(40, 40);
        ovalTool.setTooltip(ovalTooltip);

        ToggleButton zoomTool = new ToggleButton("Z");
        zoomTool.setPrefSize(40, 40);
        zoomTool.setTooltip(zoomTooltip);

        ToggleGroup toolToggle = new ToggleGroup();
        toolToggle.getToggles().addAll(selectTool, rectTool, ovalTool, zoomTool);

        TilePane left = new TilePane(selectTool, rectTool, ovalTool, zoomTool);

        BorderPane root = new BorderPane(center, top, right, bottom, left);

        Scene scene = new Scene(root, width, height);
        scene.getStylesheets().addAll("custom.css");

        return scene;
    }

    // Meetod, mis genereerib Scene objekti RectTooli poolt väljakutsutud modaalaknale
    // Hetkel ei tee see meetod midagi
    public static Scene getRectModalScene() {
        Parent modalRoot = null;
        return new Scene(modalRoot);
    }

    // Meetod, mis genereerib Scene objekti RectTooli poolt väljakutsutud modaalaknale
    // Hetkel ei tee see meetod midagi
    public static Scene getOvalModalScene() {
        Parent modalRoot = null;
        return new Scene(null);
    }


}
