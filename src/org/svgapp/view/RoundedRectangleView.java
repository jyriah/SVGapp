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
* vajutamine peale laiuse, kõrguse ja nurga raadiuse
* valimimist joonistab Pane objektile ümarate
* nurkadega nelinurga
* */
public class RoundedRectangleView extends Stage {
    MouseEvent mouseEvent;
    Pane pane;

    // Kujundi laius kõrgus sildid
    Label widthLbl = new Label("Width:");
    Label heightLbl = new Label("Height:");
    Label cornerLbl = new Label("Corner radius:");

    // Tekstiväljad, milles saab kasutaja ära määrata kujundi täpse laiuse ja kõrguse
    TextField widthFld = new TextField();
    TextField heightFld = new TextField();
    TextField cornerFld = new TextField();

    // GridPane mille peal on sildid ja tekstiväljad
    GridPane widthHeightInfo = new GridPane();

    // Nupud valiku kinnitamiseks või tühistamiseks
    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Cancel");

    // HBox mille sees on nupud
    HBox btnBox = new HBox();

    // VBox mille sees on kõik akna elemendid
    VBox root = new VBox();

    // Konstruktor, mis paigutab kõik UI-elemendid kujundi akna jaoks valmis
    public RoundedRectangleView(MouseEvent mouseEvent, Pane pane, Controller controller) {
        widthFld.setMaxWidth(60);
        heightFld.setMaxWidth(60);
        cornerFld.setMaxWidth(60);
        widthHeightInfo.setHgap(5);
        widthHeightInfo.setVgap(5);
        widthHeightInfo.add(widthLbl, 1, 1);
        widthHeightInfo.add(heightLbl, 1, 2);
        widthHeightInfo.add(cornerLbl, 1, 3);
        widthHeightInfo.add(widthFld, 2, 1);
        widthHeightInfo.add(heightFld, 2, 2);
        widthHeightInfo.add(cornerFld, 2, 3);
        this.mouseEvent = mouseEvent;
        this.pane = pane;

        okBtn.setDefaultButton(true);
        okBtn.setOnAction(e -> {
            drawRoundedRectangle(mouseEvent, pane, controller);
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

        this.setTitle("Rounded Rectangle");

    }
    // Meetod lisab pane-ile ümarate nurkadega nelinurga
    public void drawRoundedRectangle(MouseEvent mouseEvent, Pane pane, Controller controller) {
        double width = Double.parseDouble(widthFld.getText().trim());
        double height = Double.parseDouble(heightFld.getText().trim());
        double arcWidthHeight = Double.parseDouble(cornerFld.getText().trim());

        Rectangle rect = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), width, height);

        rect.setArcHeight(arcWidthHeight);
        rect.setArcWidth(arcWidthHeight);

        rect.setFill(controller.getCurrentFillValue());
        rect.setStroke(controller.getCurrentStrokeValue());

        rect.setOnMousePressed(event -> controller.initStartingPoint(event));
        rect.setOnMouseReleased(event -> controller.addShape((Shape)event.getSource(), event));
        rect.setOnMouseDragged(event -> controller.moveShapes(event));
        rect.setOnMouseClicked(event -> event.consume());

        pane.getChildren().add(rect);

    }
}

