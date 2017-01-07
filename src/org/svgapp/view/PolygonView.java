package org.svgapp.view;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Shape;
import javafx.stage.Stage;
import org.svgapp.controller.Controller;

/*
* Klass, mis tekitab modaalakna kui kasutaja
* on valinud tähekujundi tööriista ja klikib Pane objektil
* documentPane SVGAppView klassis. OK nupule
* vajutamine peale raadiuse ja nurkade arvu
* valimimist joonistab Pane objektile hulktahuka
* */

public class PolygonView extends Stage {
    MouseEvent mouseEvent;
    Pane pane;

    // Kujundi laius kõrgus sildid
    Label radiusLbl = new Label("Radius:");
    Label sidesLbl = new Label("Sides:");

    // Tekstiväljad, milles saab kasutaja ära määrata kujundi raadiuse
    TextField radiusFld = new TextField();
    TextField sidesFld = new TextField();

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
    public PolygonView(MouseEvent mouseEvent, Pane pane, Controller controller) {
        radiusFld.setMaxWidth(60);
        sidesFld.setMaxWidth(60);
        widthHeightInfo.setHgap(5);
        widthHeightInfo.setVgap(5);
        widthHeightInfo.add(radiusLbl, 1, 1);
        widthHeightInfo.add(sidesLbl, 1, 2);
        widthHeightInfo.add(radiusFld, 2, 1);
        widthHeightInfo.add(sidesFld, 2, 2);
        this.mouseEvent = mouseEvent;
        this.pane = pane;

        okBtn.setDefaultButton(true);
        okBtn.setOnAction(e -> {
            drawPolygon(mouseEvent, pane, controller);
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

        this.setTitle("Polygon");

    };

    public void drawPolygon(MouseEvent mouseEvent, Pane pane, Controller controller) {
        double centerX = mouseEvent.getX();
        double centerY = mouseEvent.getY();
        double radius = Double.parseDouble(radiusFld.getText().trim());
        int sides = Integer.parseInt(sidesFld.getText().trim());

        double[] radianValues = new double[sides];
        double[] points = new double[sides*2];

        int pointer = 0;

        for (int i = 0; i < sides; i++) {
            radianValues[i] = (Math.PI * 2/sides) * i;
        }

        for (int i = 0; i < sides*2; i++) {
            if(i % 2 ==0) {
                points[i] = centerX + radius * Math.cos(radianValues[pointer]);
            } else {
                points[i] = centerY + radius * Math.sin(radianValues[pointer]);
                pointer++;
            }
        }
        Polygon polygon = new Polygon(points);
        polygon.setFill(controller.getCurrentFillValue());
        polygon.setStroke(controller.getCurrentStrokeValue());

        polygon.setOnMousePressed(event -> controller.initStartingPoint(event));
        polygon.setOnMouseReleased(event -> controller.addShape((Shape)event.getSource(), event));
        polygon.setOnMouseDragged(event -> controller.moveShapes(event));
        polygon.setOnMouseClicked(event -> event.consume());

        pane.getChildren().add(polygon);

    }
}

