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

    // Värvide kontrollelemendi paneel
    GridPane colorControlBox = new GridPane();

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

        // Värvide juhtpaneeli
        colorControlBox.setVgap(10);
        colorControlBox.setStyle("-fx-background-color: #888;" +
                "-fx-border-color: #ccc;" +
                "-fx-border-width: 2;" +
                "-fx-padding: 10;");

        Slider redSlider = new Slider(0, 255, 45);
        Slider greenSlider = new Slider(0, 255, 55);
        Slider blueSlider = new Slider(0, 255, 65);


        TextField redValueTextField = new TextField(Integer.toString((int) redSlider.getValue()));
        redValueTextField.setMaxWidth(60);
        TextField greenValueTextField = new TextField(Integer.toString((int) greenSlider.getValue()));
        greenValueTextField.setMaxWidth(60);
        TextField blueValueTextField = new TextField(Integer.toString((int) blueSlider.getValue()));
        blueValueTextField.setMaxWidth(60);


        RadioButton fillRadioButton = new RadioButton("Fill");
        RadioButton strokeRadioButton = new RadioButton("Stroke");
        ToggleGroup fillStrokeGroup = new ToggleGroup();
        fillStrokeGroup.getToggles().addAll(fillRadioButton, strokeRadioButton);


        //fillRedValue.setOnKeyPressed();
        colorControlBox.addRow(1, fillRadioButton, strokeRadioButton);
        colorControlBox.add(new Label("<placeholder for fill or stroke>"), 0, 2, 3, 1);
        colorControlBox.addRow(3, new Label("Red"), redSlider, redValueTextField);
        colorControlBox.addRow(4, new Label("Green"), greenSlider, greenValueTextField);
        colorControlBox.addRow(5, new Label("Blue"), blueSlider, blueValueTextField);

        // SVGAppViewle dokumendiala, menüüriba, tööriistakasti ja kontrollelementide paneeli lisamine
        this.setCenter(documentPane);
        this.setTop(menuBar);
        this.setLeft(toolBox);
        this.setRight(colorControlBox);
    }
}
