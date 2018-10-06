package com.smartchart.teamdreammachine.smartchart;

public class DataPoints {
    private final int x;
    private final int y;
    private final String name;

    public DataPoints(int x, int y, String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }

    public int getX() { return(this.x); }
    public int getY() { return(this.y); }
    public String getName() {return(this.name); }
}
