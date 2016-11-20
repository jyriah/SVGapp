package org.svgapp.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

/**
 * Created by jyri on 11/09/16.
 */
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
    public RoundedRectangleView(MouseEvent mouseEvent, Pane pane) {
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
            drawRoundedRectangle(mouseEvent, pane);
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

    public void drawRoundedRectangle(MouseEvent mouseEvent, Pane pane) {
        Rectangle rect = new Rectangle(mouseEvent.getX(), mouseEvent.getY(), 100, 100);
        rect.setWidth(Double.parseDouble(widthFld.getText().trim()));
        rect.setHeight(Double.parseDouble(heightFld.getText().trim()));
        rect.setArcHeight(Double.parseDouble(cornerFld.getText().trim()));
        rect.setArcWidth(Double.parseDouble(cornerFld.getText().trim()));
        pane.getChildren().add(rect);

    }
}

