package com.smartchart.teamdreammachine.smartchart;

import java.util.ArrayList;
import java.util.LinkedList;

public class Graph {

    private final int id;
    private String name;
    private String type;
    private String xAxisLabel;
    private String yAxisLabel;
    private LinkedList<String> categories;
    private ArrayList<DataPoints> pointList;

    public Graph(int id, String name, String xAxisLabel, String yAxisLabel, String type, LinkedList<String> categories, ArrayList<DataPoints> pointList) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.xAxisLabel = xAxisLabel;
        this.yAxisLabel = yAxisLabel;
        this.categories = categories;
        this.pointList = pointList;
    }
    public int getID() {return(this.id);}
    public String getName() {return(this.name);}
    public String getType() {return(this.type);}
    public String xAxisLabel() {return(this.xAxisLabel);}
    public String yAxisLabel() {return(this.yAxisLabel);}
    public LinkedList<String> getCategories() {return(this.categories);}
    public ArrayList<DataPoints> getPointList() {return(this.pointList);}

}
