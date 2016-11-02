package org.svgapp.model;

/**
 * Created by jyri on 11/3/16.
 */
public class Model {
    String toolName="";

    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
        System.out.println(toolName);
    }
}
