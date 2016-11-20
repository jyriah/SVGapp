package org.svgapp.model;

public class Model {
    // Tööriistakastist omistatud tööriistanimi
    String toolName="";
    

    // getterid setterid
    public String getToolName() {
        return toolName;
    }

    public void setToolName(String toolName) {
        this.toolName = toolName;
        System.out.println(toolName);
    }
}
