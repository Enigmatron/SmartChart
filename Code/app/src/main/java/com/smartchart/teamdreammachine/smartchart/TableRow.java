package com.smartchart.teamdreammachine.smartchart;

import java.util.HashMap;

public class TableRow {

    private final int id;
    private String name;
    private float x;
    private float y;
    private boolean validX;
    private boolean validY;
    private boolean validXY;
    private HashMap<String, String> valuePairs;

    public TableRow(int id, String name, float x, float y, HashMap<String, String> valuePairs) {
        this.id = id; this.name = name; this.x = x; validX =true; this.y = y; validY = true; validXY = true; this.valuePairs = valuePairs;
    }
    public TableRow(int id, String name, float x, HashMap<String, String> valuePairs) {
        this.id = id; this.name = name; this.x = x; validX =true; this.valuePairs = valuePairs;
    }
    public TableRow(int id, String name, HashMap<String, String> valuePairs) {
        this.id = id; this.name = name; this.valuePairs = valuePairs;
    }



    public int getID() {return(this.id);}
    public String getName() {return(this.name);}
    public void setName(String name) {this.name=name;}
    public float getX() {return(this.x);}
    public void setX(int x) {this.x=x; validX=true;}
    public float getY() {return(this.y);}
    public void setY(int y) {this.y=y; validY=true;}
    public boolean checkX() {return(this.validX);}
    public boolean checkY() {return(this.validY);}
    public boolean checkXY() {return(this.validXY);}
    public float removeX() {this.validXY = false; this.validX = false; float temp = this.x; this.x = 0; return(temp);}
    public float removeY() {this.validXY = false; this.validY = false; float temp = this.y; this.y = 0; return(temp);}
    public void removeXY() {this.validXY = false; this.validX = false; this.validY = false; this.x = 0; this.y = 0;}
    public HashMap<String, String> getPairs() {return(this.valuePairs);}
    public void setPair(String key, String value) {this.valuePairs.put(key, value);}
    public String removePair(String key) {return(this.valuePairs.remove(key));}
    public HashMap<String, String> removeValuePairs() {HashMap<String, String> temp = this.valuePairs; this.valuePairs = null; return(temp);}


}