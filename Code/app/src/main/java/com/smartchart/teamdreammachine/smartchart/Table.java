package com.smartchart.teamdreammachine.smartchart;


import java.util.HashMap;
import java.util.Iterator;

public class Table {

    private final int id;
    private HashMap<Integer, TableRow> table;

    public Table(int id) {
        this.id = id;
    }

    public void addRow(TableRow row) {
        table.put(row.getID(), row);
    }

    public TableRow getRow(Integer id) {
        return(table.get(id));
    }

    public TableRow removeRow(Integer id) {
        return(table.remove(id));
    }

    public Iterator<Integer> idIterator() {
        return(table.keySet().iterator());
    }

    public Iterator<HashMap.Entry<Integer,TableRow>> entryIterator() {
        return(table.entrySet().iterator());
    }


}
