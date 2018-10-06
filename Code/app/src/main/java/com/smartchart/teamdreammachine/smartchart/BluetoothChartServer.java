package com.smartchart.teamdreammachine.smartchart;

import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.*;

import android.util.Log;
import android.webkit.URLUtil;

import java.io.IOException;
import java.util.UUID;

import static android.content.ContentValues.TAG;

public class BluetoothChartServer extends Thread{
    private final BluetoothServerSocket mmServerSocket;

    public BluetoothChartServer(){
        BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        BluetoothServerSocket tmp = null;

        if (mBluetoothAdapter == null) {
            // Device doesn't support Bluetooth
        }
        else {
            // Use a temporary object that is later assigned to mmServerSocket
            // because mmServerSocket is final.
//            BluetoothServerSocket tmp = null;
            try {
                // MY_UUID is the app's UUID string, also used by the client code.
                tmp = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(
                        "hi",UUID.randomUUID());//this wont work
//                        NAME, MY_UUID);
            } catch (IOException e) {
                Log.e(TAG, "Socket's listen() method failed", e);
            }
        }
            mmServerSocket = tmp;

    }

    public void run() {
        BluetoothSocket socket = null;
        // Keep listening until exception occurs or a socket is returned.
        while (true) {
            try {
                socket = mmServerSocket.accept();
            } catch (IOException e) {
                Log.e(TAG, "Socket's accept() method failed", e);
                break;
            }

            if (socket != null) {
                // A connection was accepted. Perform work associated with
                // the connection in a separate thread.
                manageMyConnectedSocket(socket);
                try {
                    mmServerSocket.close();
                }
                catch(Exception e){
                    e.printStackTrace();
                }
                break;
            }
        }
    }

    private void manageMyConnectedSocket(BluetoothSocket socket) {

    }

    // Closes the connect socket and causes the thread to finish.
    public void cancel() {
        try {
            mmServerSocket.close();
        } catch (IOException e) {
            Log.e(TAG, "Could not close the connect socket", e);
        }
    }


}
