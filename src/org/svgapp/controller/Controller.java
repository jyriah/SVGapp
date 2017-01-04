package org.svgapp.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import org.svgapp.model.Model;
import org.svgapp.view.*;



public class Controller {
    Model model = new Model();

    public Controller(Model model) {
        this.model = model;
    }

    public void setTool(String toolName) {
        model.setToolName(toolName);
    }

    public void modalOpen(MouseEvent mouseEvent, Pane pane, Controller controller) {
        String toolName = model.getToolName();
        if(toolName.equals("Rectangle")) {
            RectangleView modalWindow = new RectangleView(mouseEvent, pane, controller);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("RoundedRectangle")) {
            RoundedRectangleView modalWindow = new RoundedRectangleView(mouseEvent, pane, controller);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("Ellipse")) {
            EllipseView modalWindow = new EllipseView(mouseEvent, pane, controller);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("Polygon")) {
            PolygonView modalWindow = new PolygonView(mouseEvent, pane, controller);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("Star")) {
            StarView modalWindow = new StarView(mouseEvent, pane, controller);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else {
            System.out.println("Some other tool!");
        }
    }

    public void fileNew() {
        System.out.println("Creating a new file");
    }

    public void fileSave() {
        System.out.println("Saving the file");
    }

    public void fileOpen() {
        System.out.println("Opening the selected file in FileChooser");
    }

    public void processExit() {
        System.exit(0);
    }

    public Color getCurrentFillValue() {
        Color color = model.getFillColor();
        return color;
    }

    public void setCurrentFillValue(Color color) {
        model.setFillColor(color);
    }

    public Color getCurrentStrokeValue() {
        Color color = model.getStrokeColor();
        return color;
    }

    public void setCurrentStrokeValue(Color color) {
        model.setStrokeColor(color);
    }

    public boolean isFillSelected() {
        return model.isFillSelected();
    }

    public void setFillSelected(boolean selected) {
        model.setFillSelected(selected);
    }
}
