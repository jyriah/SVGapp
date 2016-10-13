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
 * Klass, mis laiendab BorderPane objekti (Peaaken)
 */
public class SVGMainView  extends BorderPane {

    Node center = null;
    Node right = null;
    Node bottom = null;

    //Ülemine menüü
    Menu fileMenu = new Menu("File");
    Menu editMenu = new Menu("Edit");
    Menu typeMenu = new Menu("Type");
    Menu helpMenu = new Menu("Help");

    // Tooltipid tööriistanuppudele
    Tooltip selectTooltip = new Tooltip("Select tool");
    Tooltip rectTooltip = new Tooltip("Rectangle tool");
    Tooltip ovalTooltip = new Tooltip("Oval tool");
    Tooltip zoomTooltip = new Tooltip("Zoom tool");

    // Vasak tööriistamenüü
    ToggleButton selectTool = new ToggleButton("S");


    ToggleButton rectTool = new ToggleButton("R");
    ToggleButton ovalTool = new ToggleButton("O");

    ToggleButton zoomTool = new ToggleButton("Z");

    ToggleGroup toolToggle = new ToggleGroup();


    TilePane left = new TilePane(selectTool, rectTool, ovalTool, zoomTool);

    // Peaakna konstruktor
    public SVGMainView() {
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
        ovalTool.setPrefSize(40, 40);
        ovalTool.setTooltip(ovalTooltip);
        zoomTool.setPrefSize(40, 40);
        zoomTool.setTooltip(zoomTooltip);


        toolToggle.getToggles().addAll(selectTool, rectTool, ovalTool, zoomTool);

        HBox top = new HBox();
        top.getChildren().addAll(menuBar);

        this.setCenter(center);
        this.setTop(top);
        this.setRight(right);
        this.setBottom(bottom);
        this.setLeft(left);
    }

}
