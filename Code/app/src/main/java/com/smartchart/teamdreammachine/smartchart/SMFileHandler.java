package com.smartchart.teamdreammachine.smartchart;

import java.io.File;

class SMFileHandler {
    public enum StoreType{
        graph,
        dataset,

    }
    private static final SMFileHandler ourInstance = new SMFileHandler();

    static SMFileHandler getInstance() {
        return ourInstance;
    }
    static File currNameFile;

    private SMFileHandler() {

    }

    boolean setCurrentFile(String name){
        return(false);
    }
}
