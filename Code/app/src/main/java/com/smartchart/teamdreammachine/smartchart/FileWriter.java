package com.smartchart.teamdreammachine.smartchart;

import java.io.File;

class FileWriter {
    public enum StoreType{
        graph,
        dataset,

    }
    private static final FileWriter ourInstance = new FileWriter();

    static FileWriter getInstance() {
        return ourInstance;
    }
    static File currNameFile;

    private FileWriter() {

    }

    boolean setCurrentFile(String name){

    }
}
