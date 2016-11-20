package org.svgapp.controller;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import org.svgapp.model.Model;
import org.svgapp.view.EllipseView;
import org.svgapp.view.RectangleView;
import org.svgapp.view.RoundedRectangleView;

/**
 * Created by jyri on 11/2/16.
 */
public class Controller {
    Model model = new Model();

    public Controller(Model model) {
        this.model = model;
    }

    public void setTool(String toolName) {
        model.setToolName(toolName);
    }

    public void modalOpen(MouseEvent mouseEvent, Pane pane) {
        String toolName = model.getToolName();
        if(toolName.equals("Rectangle")) {
            RectangleView modalWindow = new RectangleView(mouseEvent, pane);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("RoundedRectangle")) {
            RoundedRectangleView modalWindow = new RoundedRectangleView(mouseEvent, pane);
            modalWindow.initOwner(pane.getScene().getWindow());
            modalWindow.initModality(Modality.APPLICATION_MODAL);
            modalWindow.show();
        } else if(toolName.equals("Ellipse")) {
            EllipseView modalWindow = new EllipseView(mouseEvent, pane);
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

}
