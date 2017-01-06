package org.svgapp.view;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
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
    Menu selectMenu = new Menu("Select");
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

    // Põhivärvide swatch
    GridPane colorSwatches = new GridPane();

    Slider redSlider = new Slider(0, 255, controller.getCurrentFillValue().getRed()*255);
    Slider greenSlider = new Slider(0, 255, controller.getCurrentFillValue().getGreen()*255);
    Slider blueSlider = new Slider(0, 255, controller.getCurrentFillValue().getBlue()*255);

    Label redValueLabel;
    Label greenValueLabel;
    Label blueValueLabel;

    Rectangle fillColor;
    Rectangle strokeColor;

    Rectangle white = new Rectangle(30, 30);
    Rectangle gray = new Rectangle(30, 30);
    Rectangle black = new Rectangle(30, 30);
    Rectangle red = new Rectangle(30, 30);
    Rectangle green = new Rectangle(30, 30);
    Rectangle blue = new Rectangle(30, 30);
    Rectangle warmlightred = new Rectangle(30, 30);
    Rectangle warmlightgreen = new Rectangle(30, 30);
    Rectangle warmlightblue = new Rectangle(30, 30);
    Rectangle coldlightred = new Rectangle(30, 30);
    Rectangle coldlightgreen = new Rectangle(30, 30);
    Rectangle coldlightblue = new Rectangle(30, 30);

    VBox rightPanel = new VBox();

    String gridPaneStyle = "-fx-background-color: #888;" +
            "-fx-border-color: #ccc;" +
            "-fx-border-width: 2;" +
            "-fx-padding: 10;";

    // Peaakna konstruktor
    public SVGAppView() {
        // Menüüribale menüüde lisamine
        menuBar.getMenus().addAll(fileMenu, editMenu, selectMenu, helpMenu);

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

        MenuItem selectAll = new MenuItem("Select _all");
        selectAll.setOnAction(event -> controller.selectAll(documentPane));

        selectMenu.getItems().addAll(selectAll);

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
        documentPane.setOnMouseClicked(event -> controller.modalOpen(event, documentPane, controller));

        // Värvide juhtpaneeli
        colorControlBox.setVgap(10);
        colorControlBox.setHgap(10);
        colorControlBox.setStyle(gridPaneStyle);

        // Slideri väärtustele listeneri lisamine
        redSlider.valueProperty().addListener(this::redSliderChanged);
        greenSlider.valueProperty().addListener(this::greenSliderChanged);
        blueSlider.valueProperty().addListener(this::blueSliderChanged);

        // Punase slideri paremal pool olev silt, mis kuvab slideri positsiooni
        redValueLabel = new Label(Integer.toString((int) redSlider.getValue()));
        redValueLabel.setMinWidth(25);

        // Rohelis slideri paremal pool olev silt, mis kuvab slideri positsiooni
        greenValueLabel = new Label(Integer.toString((int) redSlider.getValue()));
        greenValueLabel.setMinWidth(25);

        // Sinise slideri paremal pool olev silt, mis kuvab slideri positsiooni
        blueValueLabel = new Label(Integer.toString((int) redSlider.getValue()));
        blueValueLabel.setMinWidth(25);

        // RadioButton, millega saab valida kujundi täite
        RadioButton fillRadioButton = new RadioButton("Fill");
        fillRadioButton.setSelected(true);

        // RadioButton, millega saab valida kujundi joone
        RadioButton strokeRadioButton = new RadioButton("Stroke");

        // RadioButtonite grupeerimine
        ToggleGroup fillStrokeGroup = new ToggleGroup();
        fillStrokeGroup.getToggles().addAll(fillRadioButton, strokeRadioButton);
        fillStrokeGroup.selectedToggleProperty().addListener(this::radioButtonChanged); // RadidButtonite grupile
        // listeneri lisamine

        // fillColor ja strokeColor ruutude initsialiseerimine ja mudelis
        // määratud vaikevärvide lisamine ruudu täitele programmi käivitamisfaasis
        fillColor = new Rectangle(30, 30);
        fillColor.setFill(controller.getCurrentFillValue());
        strokeColor = new Rectangle(30, 30);
        strokeColor.setFill(controller.getCurrentStrokeValue());



        colorControlBox.addRow(1, fillRadioButton, fillColor);
        colorControlBox.addRow(2, strokeRadioButton, strokeColor);
        colorControlBox.addRow(3, new Label("Red"), redSlider, redValueLabel);
        colorControlBox.addRow(4, new Label("Green"), greenSlider, greenValueLabel);
        colorControlBox.addRow(5, new Label("Blue"), blueSlider, blueValueLabel);

        black.setFill(Color.BLACK);
        black.setStroke(Color.WHITE);
        black.setOnMouseClicked(event -> setCurrentColor((Color)black.getFill()));

        gray.setFill(Color.GRAY);
        gray.setStroke(Color.WHITE);
        gray.setOnMouseClicked(event -> setCurrentColor((Color)gray.getFill()));

        white.setFill(Color.WHITE);
        white.setStroke(Color.BLACK);
        white.setOnMouseClicked(event -> setCurrentColor((Color)white.getFill()));

        red.setFill(Color.RED);
        red.setStroke(Color.WHITE);
        red.setOnMouseClicked(event -> setCurrentColor((Color)red.getFill()));

        green.setFill(Color.GREEN);
        green.setStroke(Color.WHITE);
        green.setOnMouseClicked(event -> setCurrentColor((Color)green.getFill()));

        blue.setFill(Color.BLUE);
        blue.setStroke(Color.WHITE);
        blue.setOnMouseClicked(event -> setCurrentColor((Color)blue.getFill()));

        warmlightred.setFill(Color.rgb(255, 127, 0));
        warmlightred.setStroke(Color.WHITE);
        warmlightred.setOnMouseClicked(event -> setCurrentColor((Color)warmlightred.getFill()));

        warmlightgreen.setFill(Color.rgb(127, 255, 0));
        warmlightgreen.setStroke(Color.WHITE);
        warmlightgreen.setOnMouseClicked(event -> setCurrentColor((Color)warmlightgreen.getFill()));

        warmlightblue.setFill(Color.rgb(127, 0, 255));
        warmlightblue.setStroke(Color.WHITE);
        warmlightblue.setOnMouseClicked(event -> setCurrentColor((Color)warmlightblue.getFill()));

        coldlightred.setFill(Color.rgb(255, 0, 255));
        coldlightred.setStroke(Color.WHITE);
        coldlightred.setOnMouseClicked(event -> setCurrentColor((Color)coldlightred.getFill()));

        coldlightgreen.setFill(Color.rgb(0, 255, 127));
        coldlightgreen.setStroke(Color.WHITE);
        coldlightgreen.setOnMouseClicked(event -> setCurrentColor((Color)coldlightgreen.getFill()));

        coldlightblue.setFill(Color.rgb(0, 127, 255));
        coldlightblue.setStroke(Color.WHITE);
        coldlightblue.setOnMouseClicked(event -> setCurrentColor((Color)coldlightblue.getFill()));

        colorSwatches.setVgap(10);
        colorSwatches.setHgap(3);
        colorSwatches.setStyle(gridPaneStyle);
        colorSwatches.add(new Label("Color swatches"), 0, 1, 6, 1);
        colorSwatches.addRow(2, black, gray, white, red, green, blue);
        colorSwatches.addRow(3, warmlightred, warmlightgreen, warmlightblue,
                coldlightred, coldlightgreen, coldlightblue);

        rightPanel.getChildren().addAll(colorControlBox, colorSwatches);

        // SVGAppViewle dokumendiala, menüüriba, tööriistakasti ja kontrollelementide paneeli lisamine
        this.setCenter(documentPane);
        this.setTop(menuBar);
        this.setLeft(toolBox);
        this.setRight(rightPanel);
    }

    public void redSliderChanged(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
        int red = (int) redSlider.getValue();
        redValueLabel.setText(String.valueOf(red));

        if (controller.isFillSelected()) {
            int green = (int) (controller.getCurrentFillValue().getGreen()*255);
            int blue = (int) (controller.getCurrentFillValue().getBlue()*255);

            Color color = Color.rgb(red, green, blue);

            controller.setCurrentFillValue(color);

            fillColor.setFill(controller.getCurrentFillValue());
        } else {
            int green = (int) (controller.getCurrentStrokeValue().getGreen()*255);
            int blue = (int) (controller.getCurrentStrokeValue().getBlue()*255);

            Color color = Color.rgb(red, green, blue);

            controller.setCurrentStrokeValue(color);

            strokeColor.setFill(controller.getCurrentStrokeValue());
        }
    }

    public void greenSliderChanged(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
        int green = (int) greenSlider.getValue();
        greenValueLabel.setText(String.valueOf(green));

        if (controller.isFillSelected()) {
            int red = (int) (controller.getCurrentFillValue().getRed()*255);
            int blue = (int) (controller.getCurrentFillValue().getBlue()*255);

            Color color = Color.rgb(red, green, blue);

            controller.setCurrentFillValue(color);

            fillColor.setFill(color);
        } else {
            int red = (int) (controller.getCurrentStrokeValue().getRed()*255);
            int blue = (int) (controller.getCurrentStrokeValue().getBlue()*255);

            Color color = Color.rgb(red, green, blue);

            controller.setCurrentStrokeValue(color);

            strokeColor.setFill(controller.getCurrentStrokeValue());
        }
    }

    public void blueSliderChanged(ObservableValue<? extends Number> prop, Number oldValue, Number newValue) {
        int blue = (int) blueSlider.getValue();
        blueValueLabel.setText(String.valueOf(blue));

        if (controller.isFillSelected()) {
            int red = (int) (controller.getCurrentFillValue().getRed()*255);
            int green = (int) (controller.getCurrentFillValue().getGreen()*255);
            Color color = Color.rgb(red, green, blue);

            controller.setCurrentFillValue(color);

            fillColor.setFill(color);
        } else {
            int red = (int) (controller.getCurrentStrokeValue().getRed()*255);
            int green = (int) (controller.getCurrentStrokeValue().getGreen()*255);

            Color color = Color.rgb(red, green, blue);

            controller.setCurrentStrokeValue(color);

            strokeColor.setFill(controller.getCurrentStrokeValue());
        }
    }

    public void radioButtonChanged(ObservableValue<? extends  Toggle> observable, Toggle oldBtn, Toggle newBtn) {
        String selectedLabel = ((Labeled)newBtn).getText();

        if(selectedLabel.equals("Fill")) {
            controller.setFillSelected(true);
            controller.setFillSlider(redSlider, greenSlider, blueSlider);

        } else {
            controller.setFillSelected(false);
            controller.setStrokeSlider(redSlider, greenSlider, blueSlider);
        }
    }

    public void setCurrentColor(Color color) {
        if(controller.isFillSelected()) {
            controller.setCurrentFillValue(color);
            fillColor.setFill(color);
            controller.setFillSlider(redSlider, greenSlider, blueSlider);

        } else {
            controller.setCurrentStrokeValue(color);
            strokeColor.setFill(color);
            controller.setStrokeSlider(redSlider, greenSlider, blueSlider);
        }
    }
}
