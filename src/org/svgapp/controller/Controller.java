package org.svgapp.controller;

import org.svgapp.model.Model;

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

    public void processExit() {
        System.exit(0);
    }

    public void processOpen() {
        System.out.println("processing opening the file");
    }
}
