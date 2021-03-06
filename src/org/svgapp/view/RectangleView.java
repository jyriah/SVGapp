package org.svgapp.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.svgapp.controller.Controller;

/*
* Klass, mis tekitab modaalakna kui kasutaja
* on valinud tähekujundi tööriista ja klikib Pane objektil
* documentPane SVGAppView klassis. OK nupule
* vajutamine peale laiuse ja kõrguse
* valimimist joonistab Pane objektile nelinurga
* */

public class RectangleView extends Stage {
    MouseEvent mouseEvent;
    Pane pane;

    // Kujundi laius kõrgus sildid
    private Label widthLbl = new Label("Width:");
    private Label heightLbl = new Label("Height:");

    // Tekstiväljad, milles saab kasutaja ära määrata kujundi täpse laiuse ja kõrguse
    private TextField widthFld = new TextField();
    private TextField heightFld = new TextField();

    // GridPane mille peal on sildid ja tekstiväljad
    private GridPane widthHeightInfo = new GridPane();

    // Nupud valiku kinnitamiseks või tühistamiseks
    private Button okBtn = new Button("OK");
    private Button cancelBtn = new Button("Cancel");

    // HBox mille sees on nupud
    private HBox btnBox = new HBox();

    // VBox mille sees on kõik akna elemendid
    private VBox root = new VBox();

    // Konstruktor, mis paigutab kõik UI-elemendid kujundi akna jaoks valmis
    public RectangleView(MouseEvent mouseEvent, Pane pane, Controller controller) {
        widthFld.setMaxWidth(60);
        heightFld.setMaxWidth(60);
        widthHeightInfo.setHgap(5);
        widthHeightInfo.setVgap(5);
        widthHeightInfo.add(widthLbl, 1, 1);
        widthHeightInfo.add(heightLbl, 1, 2);
        widthHeightInfo.add(widthFld, 2, 1);
        widthHeightInfo.add(heightFld, 2, 2);
        this.mouseEvent = mouseEvent;
        this.pane = pane;

        okBtn.setDefaultButton(true);
        okBtn.setOnAction(e -> {
            drawRectangle(mouseEvent, pane, controller);
            this.close();
        });

        cancelBtn.setCancelButton(true);
        cancelBtn.setOnAction(e -> this.close());

        btnBox.getChildren().addAll(okBtn, cancelBtn);
        btnBox.setSpacing(10);
        btnBox.setAlignment(Pos.TOP_CENTER);

        root.getChildren().addAll(widthHeightInfo, btnBox);

        root.setSpacing(10);
        root.setStyle("-fx-padding: 15;");

        Scene scene = new Scene(root);

        this.setScene(scene);

        this.setTitle("Rectangle");

    }

    public void drawRectangle(MouseEvent mouseEvent, Pane pane, Controller controller) {
        double width = Double.parseDouble(widthFld.getText().trim());
        double height = Double.parseDouble(heightFld.getText().trim());

        Rectangle rect = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), width, height);
        rect.setFill(controller.getCurrentFillValue());
        rect.setStroke(controller.getCurrentStrokeValue());

        rect.setOnMousePressed(event -> controller.initStartingPoint(event));
        rect.setOnMouseReleased(event -> controller.addShape((Shape)event.getSource(), event));
        rect.setOnMouseDragged(event -> controller.moveShapes(event));
        rect.setOnMouseClicked(event -> event.consume());

        pane.getChildren().add(rect);
    }
}

