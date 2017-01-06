package org.svgapp.controller;

import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.Slider;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.stage.Modality;
import org.svgapp.model.Model;
import org.svgapp.view.*;



public class Controller {
    Model model = new Model();

    double startX;
    double startY;
    boolean dragStopped = false;

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
        } else if(toolName.equals("Select")) {
            for (int i = 0; i < model.getShapeList().size(); i++) {
                model.getShapeList().get(i).setEffect(null);
            }
            model.getShapeList().clear();
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

    public void selectAll (Pane pane) {
        ObservableList<Node> shapes = pane.getChildren();
        if (shapes.size() > 0) {
            model.getShapeList().clear();
            for (int i = 0; i < shapes.size(); i++) {
                model.getShapeList().add((Shape) shapes.get(i));
                model.getShapeList().get(i).setEffect(new DropShadow(10, 5, 5, Color.BLACK));
            }
        }
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

    public void setFillSlider(Slider slider1, Slider slider2, Slider slider3) {
        slider1.setValue(model.getFillColor().getRed()*255);
        slider2.setValue(model.getFillColor().getGreen()*255);
        slider3.setValue(model.getFillColor().getBlue()*255);
    }

    public void setStrokeSlider(Slider slider1, Slider slider2, Slider slider3) {
        slider1.setValue(model.getStrokeColor().getRed()*255);
        slider2.setValue(model.getStrokeColor().getGreen()*255);
        slider3.setValue(model.getStrokeColor().getBlue()*255);
    }

    public void addShape(Shape shape, MouseEvent event) {
        if(model.getToolName().equals("Select")) {
            if(dragStopped) {
                dragStopped = false;
                return;
            }
            if(model.getShapeList().contains(shape)) {
                model.getShapeList().remove(shape);
                shape.setEffect(null);
            } else {
                model.getShapeList().add(shape);
                shape.setEffect(new DropShadow(10, 5, 5, Color.BLACK));
            }
        }
    }

    public void moveShapes(MouseEvent event) {
        if(model.getShapeList().contains(event.getSource())) {

            for (int i = 0; i < model.getShapeList().size(); i++) {
                Shape shape = model.getShapeList().get(i);
                double deltaX = event.getX() - startX;
                double deltaY = event.getY() - startY;

                if(shape.getClass().getSimpleName().equals("Ellipse")) {
                    Ellipse ellipse = (Ellipse) shape;

                    ellipse.setCenterX(ellipse.getCenterX() + deltaX);
                    ellipse.setCenterY(ellipse.getCenterY() + deltaY);
                }
                else if(shape.getClass().getSimpleName().equals("Rectangle")) {
                    Rectangle rectangle = (Rectangle) shape;

                    rectangle.setX(rectangle.getX() + deltaX);
                    rectangle.setY(rectangle.getY() + deltaY);
                }
                else if(shape.getClass().getSimpleName().equals("Polygon")) {
                    Polygon polygon = (Polygon) shape;

                    polygon.relocate(event.getX(), event.getY());
                }
            }
            startX = event.getX();
            startY = event.getY();
            dragStopped = true;
            event.consume();
        }
    }

    public void removeShape(Shape shape) {
        model.getShapeList().remove(shape);
    }

    public void emptyShapeList() {
        model.getShapeList().clear();
    }

    public void initStartingPoint(MouseEvent event) {
        event.consume();
        startX = event.getX();
        startY = event.getY();
    }
}
