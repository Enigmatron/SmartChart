package com.smartchart.teamdreammachine.smartchart;

public class DataPoints {
    private final float x;
    private final float y;
    private final String name;

    public DataPoints(float x, float y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public float getX() { return(this.x); }
    public float getY() { return(this.y); }
    public String getName() {return(this.name); }
}
