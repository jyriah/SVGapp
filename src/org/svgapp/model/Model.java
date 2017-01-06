package org.svgapp.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Model {
    // Tööriistakastist omistatud tööriistanimi
    String toolName="";

    // Kujundi täite ja joone värvid
    Color fillColor;
    Color strokeColor;

    // Kas on valitud fill radiobutton
    boolean fillSelected = true;

    // ArrayList valitud kujunditega
    ArrayList<Shape> shapeList = new ArrayList<>();

    public Model() {
        fillColor = Color.rgb(0, 0, 0);
        strokeColor = Color.rgb(255, 255, 255);
    }

    // getterid setterid
    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
        System.out.println(toolName);
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Color getStrokeColor() {
        return strokeColor;
    }

    public void setStrokeColor(Color strokeColor) {
        this.strokeColor = strokeColor;
    }

    public void setFillSelected(boolean fillSelected) {
        this.fillSelected = fillSelected;
    }

    public boolean isFillSelected() {
        return fillSelected;
    }

    public ArrayList<Shape> getShapeList() {
        return shapeList;
    }

    public void setShapeList(ArrayList<Shape> shapeList) {
        this.shapeList = shapeList;
    }
}
