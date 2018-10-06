package com.smartchart.teamdreammachine.smartchart;

import android.content.Context;
import android.net.Uri;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.FileHandler;

class SMFileHandler {
    public enum StoreType{
        graph,
        dataset,
        user,


    }
    private static final SMFileHandler ourInstance = new SMFileHandler();
    private static Context context;
    static SMFileHandler getInstance() {
        return ourInstance;
    }
    static String currNameFile;
    static File currFile;

    static String filePath;
    static StoreType type;
//    static FileWriter;
    private SMFileHandler() {
    }


    void init(Context context){
        this.context = context.getApplicationContext();
    }

    //this sets for both read and write
    boolean setCurrentFile(StoreType type, String name){
        boolean worked = false;
        this.type = type;
        switch(type){
            case graph:
                filePath = context.getFilesDir() + File.pathSeparator + "SM" + File.pathSeparator + "graph" ;
                break;
            case dataset:
                filePath = context.getFilesDir() + File.pathSeparator + "SM" + File.pathSeparator + "dataset";
                break;
            case user:
                filePath = context.getFilesDir() + File.pathSeparator + "SM" + File.pathSeparator + "user";

                break;
        }
        try {
            filePath = Uri.parse(filePath).getLastPathSegment();
            currFile = new File(filePath+currNameFile+".xml");
            worked = true;
        } catch (Exception e) {
            // Error while creating file
        }
        return worked;
    }

    FileOutputStream readCurrentFile(){

        try{
            FileOutputStream outputStream = new FileOutputStream(currFile);
            return outputStream;
        }
        catch(Exception e){

        }
        return null;
    }

    void writeCurrentFile(String content){
        try {
            FileWriter write = new FileWriter(currFile);
            write.append(content);
        }
        catch (Exception e){

        }
        return;
    }
}
