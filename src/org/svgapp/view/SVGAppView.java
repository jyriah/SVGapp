package org.svgapp.view;

import javafx.geometry.Orientation;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import org.svgapp.controller.Controller;
import org.svgapp.model.Model;

/**
 * Klass, mis valmistab rakenduse akna jaoks kõik elemendid
 */
public class SVGAppView extends BorderPane {
    Controller controller = new Controller(new Model());

    // Rakenduse dokumendi ala, mille peal kasutaja saab luua graafikat ja muuta seda
    Pane documentPane = new Pane();

    // Rakenduse menüüd
    Menu fileMenu = new Menu("File");
    Menu editMenu = new Menu("Edit");
    Menu typeMenu = new Menu("Type");
    Menu helpMenu = new Menu("Help");

    // Menüüriba
    MenuBar menuBar = new MenuBar();

    // Tooltipid tööriistanuppudele
    Tooltip selectTooltip = new Tooltip("Select tool");
    Tooltip rectTooltip = new Tooltip("Rectangle tool");
    Tooltip ellipseTooltip = new Tooltip("Ellipse tool");
    Tooltip zoomTooltip = new Tooltip("Zoom tool");

    // Tööriistakasti toggle-nupud
    ToggleButton selectTool = new ToggleButton("S");
    ToggleButton rectTool = new ToggleButton("R");
    ToggleButton ellipseTool = new ToggleButton("E");
    ToggleButton zoomTool = new ToggleButton("Z");

    // Tööriistakasti toggle-nuppude grupp
    ToggleGroup toolToggle = new ToggleGroup();

    // Tööriistakast, mille sees on tööriitstanupud
    TilePane toolBox = new TilePane();

    // Peaakna konstruktor
    public SVGAppView() {
        // Menüüribale menüüde lisamine
        menuBar.getMenus().addAll(fileMenu, editMenu, typeMenu, helpMenu);


        MenuItem newItem = new MenuItem("_New...");
        MenuItem saveItem = new MenuItem("_Save...");
        MenuItem openItem = new MenuItem("_Open...");
        openItem.setOnAction(event -> controller.processOpen());
        MenuItem exitItem = new MenuItem(("_Exit..."));
        exitItem.setOnAction(event -> controller.processExit());
        fileMenu.getItems().addAll(newItem, saveItem, openItem, exitItem);

        selectTool.setPrefSize(40, 40);
        selectTool.setTooltip(selectTooltip);
        selectTool.setOnAction(event -> controller.setTool("Select"));

        rectTool.setTooltip(rectTooltip);
        rectTool.setPrefSize(40, 40);
        rectTool.setOnAction(event -> controller.setTool("Rectangle"));

        ellipseTool.setPrefSize(40, 40);
        ellipseTool.setTooltip(ellipseTooltip);
        ellipseTool.setOnAction(event -> controller.setTool("Ellipse"));

        zoomTool.setPrefSize(40, 40);
        zoomTool.setTooltip(zoomTooltip);
        zoomTool.setOnAction(event -> controller.setTool("Zoom"));

        toolToggle.getToggles().addAll(selectTool, rectTool, ellipseTool, zoomTool);

        toolBox.getChildren().addAll(selectTool, rectTool, ellipseTool, zoomTool);

        this.setCenter(documentPane);
        this.setTop(menuBar);
        this.setLeft(toolBox);
    }
}
