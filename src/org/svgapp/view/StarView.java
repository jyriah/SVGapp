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
import javafx.scene.shape.Polygon;
import javafx.stage.Stage;
import org.svgapp.controller.Controller;

public class StarView extends Stage {
    MouseEvent mouseEvent;
    Pane pane;

    // Kujundi laius kõrgus sildid
    private Label radius1Lbl = new Label("Radius 1:");
    private Label radius2Lbl = new Label("Radius 2:");
    private Label pointsLbl = new Label("Points:");

    // Tekstiväljad, milles saab kasutaja ära määrata kujundi raadiuse
    private TextField radius1Fld = new TextField();
    private TextField radius2Fld = new TextField();
    private TextField pointsFld = new TextField();

    // GridPane mille peal on sildid ja tekstiväljad
    private GridPane widthHeightInfo = new GridPane();

    // Nupud valiku kinnitamiseks või tühistamiseks
    Button okBtn = new Button("OK");
    Button cancelBtn = new Button("Cancel");

    // HBox mille sees on nupud
    HBox btnBox = new HBox();

    // VBox mille sees on kõik akna elemendid
    VBox root = new VBox();

    // Konstruktor, mis paigutab kõik UI-elemendid kujundi akna jaoks valmis
    public StarView(MouseEvent mouseEvent, Pane pane, Controller controller) {
        radius1Fld.setMaxWidth(60);
        radius2Fld.setMaxWidth(60);
        pointsFld.setMaxWidth(60);
        widthHeightInfo.setHgap(5);
        widthHeightInfo.setVgap(5);
        widthHeightInfo.add(radius1Lbl, 1, 1);
        widthHeightInfo.add(radius2Lbl, 1, 2);
        widthHeightInfo.add(pointsLbl, 1, 3);
        widthHeightInfo.add(radius1Fld, 2, 1);
        widthHeightInfo.add(radius2Fld, 2, 2);
        widthHeightInfo.add(pointsFld, 2, 3);
        this.mouseEvent = mouseEvent;
        this.pane = pane;

        okBtn.setDefaultButton(true);
        okBtn.setOnAction(e -> {
            drawStar(mouseEvent, pane, controller);
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

        this.setTitle("Star");

    };

    public void drawStar(MouseEvent mouseEvent, Pane pane, Controller controller) {
        // Star kujundi keskkoht
        double centerX = mouseEvent.getX();
        double centerY = mouseEvent.getY();

        // Star kujundi välimine ja sisemine raadius
        double radius1 = Double.parseDouble(radius1Fld.getText().trim());
        double radius2 = Double.parseDouble(radius2Fld.getText().trim());
        double outerRadius;
        double innerRadius;
        if(radius1 > radius2) {
            outerRadius = radius1;
            innerRadius = radius2;
        } else {
            outerRadius = radius2;
            innerRadius = radius1;
        }

        // Star kujundi tippude arv
        int points = Integer.parseInt(pointsFld.getText().trim());

        double outerPointRadian = Math.PI*2/points;
        double innerPointRadianAddition = outerPointRadian/2;

        double starXYCoords[] = new double[points*4];
        System.out.println(points*4 + ", " + outerPointRadian + ", " + innerPointRadianAddition);

        int counter = 0;

        for (int i = 0; i < points*4; i++) {
            if(i%4 == 0) {
                starXYCoords[i] = centerX + outerRadius * Math.cos(counter * outerPointRadian);
            } else if(i%4 == 1) {
                starXYCoords[i] = centerY + outerRadius * Math.sin(counter * outerPointRadian);
            } else if(i%4 == 2) {
                starXYCoords[i] = centerX + innerRadius * Math.cos((counter * outerPointRadian) + innerPointRadianAddition);
            } else {
                starXYCoords[i] = centerY + innerRadius * Math.sin((counter * outerPointRadian) + innerPointRadianAddition);
                counter++;
            }
        }

        Polygon polygon = new Polygon(starXYCoords);
        //polygon.setOnMouseClicked(event -> controller);
        polygon.setFill(controller.getCurrentFillValue());

        pane.getChildren().add(polygon);

    }
}


