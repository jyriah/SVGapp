package org.svgapp.view;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import org.svgapp.controller.Controller;
import org.svgapp.model.Model;

/**
 * Klass, mis valmistab rakenduse akna jaoks kõik elemendid
 */
public class SVGAppView extends BorderPane {
    // Kontroller, mis suhtleb View ja Modeliga
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
    Tooltip roundRectTooltip = new Tooltip("Rounded Rectangle");
    Tooltip polygonTooltip = new Tooltip("Polygon tool");
    Tooltip starTooltip = new Tooltip("Star tool");
    Tooltip ellipseTooltip = new Tooltip("Ellipse tool");
    Tooltip zoomTooltip = new Tooltip("Zoom tool");

    // Tööriistakasti toggle-nupud

    ToggleButton selectTool = new ToggleButton("");

    ToggleButton rectTool = new ToggleButton("");
    ToggleButton roundRectTool = new ToggleButton("");
    ToggleButton ellipseTool = new ToggleButton("");
    ToggleButton polygonTool = new ToggleButton("");
    ToggleButton starTool = new ToggleButton("");
    ToggleButton zoomTool = new ToggleButton("");

    // Tööriistakasti toggle-nuppude grupp
    ToggleGroup toolToggle = new ToggleGroup();

    // Tööriistakast, mille sees on tööriitstanupud
    TilePane toolBox = new TilePane();

    // Peaakna konstruktor
    public SVGAppView() {
        // Menüüribale menüüde lisamine
        menuBar.getMenus().addAll(fileMenu, editMenu, typeMenu, helpMenu);

        // Menüüdele menüüelementide ja actionite lisamine
        MenuItem newItem = new MenuItem("_New...");
        newItem.setOnAction(event -> controller.fileNew());
        
        MenuItem saveItem = new MenuItem("_Save...");
        saveItem.setOnAction(event -> controller.fileSave());
        
        MenuItem openItem = new MenuItem("_Open...");
        openItem.setOnAction(event -> controller.fileOpen());
        
        MenuItem exitItem = new MenuItem(("_Exit..."));
        exitItem.setOnAction(event -> controller.processExit());
        
        fileMenu.getItems().addAll(newItem, saveItem, openItem, exitItem);

        // Tööriistanuppudele suuruse määramine, tooltipi ja graafika lisamine. Actioni külgepanemine.
        selectTool.setPrefSize(40, 40);
        selectTool.setTooltip(selectTooltip);
        selectTool.setOnAction(event -> controller.setTool("Select"));
        selectTool.setId("toggle-select");

        rectTool.setTooltip(rectTooltip);
        rectTool.setPrefSize(40, 40);
        rectTool.setOnAction(event -> controller.setTool("Rectangle"));
        rectTool.setId("toggle-rectangle");

        roundRectTool.setTooltip(roundRectTooltip);
        roundRectTool.setPrefSize(40, 40);
        roundRectTool.setOnAction(event -> controller.setTool("RoundedRectangle"));
        roundRectTool.setId("toggle-rounded-rectangle");


        ellipseTool.setPrefSize(40, 40);
        ellipseTool.setTooltip(ellipseTooltip);
        ellipseTool.setOnAction(event -> controller.setTool("Ellipse"));
        ellipseTool.setId("toggle-ellipse");

        polygonTool.setPrefSize(40, 40);
        polygonTool.setTooltip(polygonTooltip);
        polygonTool.setOnAction(event -> controller.setTool("Polygon"));
        polygonTool.setId("toggle-polygon");

        starTool.setPrefSize(40, 40);
        starTool.setTooltip(starTooltip);
        starTool.setOnAction(event -> controller.setTool("Star"));
        starTool.setId("toggle-star");

        zoomTool.setPrefSize(40, 40);
        zoomTool.setTooltip(zoomTooltip);
        zoomTool.setOnAction(event -> controller.setTool("Zoom"));
        zoomTool.setId("toggle-zoom");

        // Toggle nuppude lisamine toggle-gruppi
        toolToggle.getToggles().addAll(
                selectTool,
                rectTool,
                roundRectTool,
                ellipseTool,
                polygonTool,
                starTool,
                zoomTool);

        // Toggle nuppude lisamine tööriistakasti
        toolBox.getChildren().addAll(
                selectTool,
                rectTool,
                roundRectTool,
                ellipseTool,
                polygonTool,
                starTool,
                zoomTool);

        // Dokumendialale lisatud hiireklikkimise kuulamine ja kontrolleri meetodi modalOpen väljakutsumine
        documentPane.setOnMouseClicked(event -> controller.modalOpen(event, documentPane));

/*        SVGPath svg = new SVGPath();
        svg.setContent("M424.8,511.5c0,0-93.2-3-63.5,67.4c29.7,70.4,113.5,35,112.2,0S424.8,511.5,424.8,511.5z");
        svg.setFill(Color.YELLOW);
        documentPane.getChildren().add(svg);*/

        // SVGAppViewle dokumendiala, menüüriba ja tööriistakasti lisamine
        this.setCenter(documentPane);
        this.setTop(menuBar);
        this.setLeft(toolBox);
    }
}