package org.svgapp.view;

import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;

/**
 * Klass, mis laiendab BorderPane objekti (Peaaken)
 */
public class MainView  extends BorderPane {
    Node right = null;
    Node bottom = null;

    // Peaakna keskmine osa
    StackPane center = new StackPane();

    // Ülemine menüü
    Menu fileMenu = new Menu("File");
    Menu editMenu = new Menu("Edit");
    Menu typeMenu = new Menu("Type");
    Menu helpMenu = new Menu("Help");

    // Tooltipid tööriistanuppudele
    Tooltip selectTooltip = new Tooltip("Select tool");
    Tooltip rectTooltip = new Tooltip("Rectangle tool");
    Tooltip ellipseTooltip = new Tooltip("Ellipse tool");
    Tooltip zoomTooltip = new Tooltip("Zoom tool");

    // Vasak tööriistamenüü
    ToggleButton selectTool = new ToggleButton("S");
    ToggleButton rectTool = new ToggleButton("R");
    ToggleButton ellipseTool = new ToggleButton("E");
    ToggleButton zoomTool = new ToggleButton("Z");

    ToggleGroup toolToggle = new ToggleGroup();

    TilePane left = new TilePane(selectTool, rectTool, ellipseTool, zoomTool);

    // Peaakna konstruktor
    public MainView() {
        createMainView();
    }

    // Meetod peaakna loomiseks
    public void createMainView() {
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, editMenu, typeMenu, helpMenu);

        selectTool.setPrefSize(40, 40);
        selectTool.setTooltip(selectTooltip);
        rectTool.setTooltip(rectTooltip);
        rectTool.setPrefSize(40, 40);
        ellipseTool.setPrefSize(40, 40);
        ellipseTool.setTooltip(ellipseTooltip);
        zoomTool.setPrefSize(40, 40);
        zoomTool.setTooltip(zoomTooltip);


        toolToggle.getToggles().addAll(selectTool, rectTool, ellipseTool, zoomTool);

        HBox top = new HBox();
        top.getChildren().addAll(menuBar);

        Rectangle rect = new Rectangle(50, 50);
        center.getChildren().addAll(rect);

        this.setCenter(center);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setLeft(left);
    }
}
