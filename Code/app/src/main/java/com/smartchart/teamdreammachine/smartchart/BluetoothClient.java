package com.smartchart.teamdreammachine.smartchart;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

//import com.google.android.things.bluetooth.BluetoothConnectionManager;

import java.util.Set;

public class BluetoothClient extends AppCompatActivity {

        private BluetoothAdapter BA;
        private Set<BluetoothDevice> pairedDevices;
//        BluetoothConnectionManager mBluetoothConnectionManager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bluetooth_client);
            BA = BluetoothAdapter.getDefaultAdapter();

            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            startActivityForResult(getVisible,0);

        }


    public void on(View v){
//        Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        if (BA.isEnabled()) {
            Toast.makeText(getApplicationContext(), "is on",Toast.LENGTH_LONG).show();

            //TODO: Is enabled continue with functionality for enabled devices...
        }else{
            //TODO: Explicitly ask user to enable bluetooth device
            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
            System.out.println("attempt to turn on");
            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
        }

//        if (!BA.isEnabled()) {
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, 0);
//            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
//        }
    }
//
//    public void off(View v){
//        BA.disable();
//        Toast.makeText(getApplicationContext(), "Turned off" ,Toast.LENGTH_LONG).show();
//    }
//
//
//    public  void visible(View v){
//        Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//        startActivityForResult(getVisible, 0);
//    }
//
//
//    public void list(View v){
//        pairedDevices = BA.getBondedDevices();
//
//        ArrayList list = new ArrayList();
//
//        for(BluetoothDevice bt : pairedDevices) list.add(bt.getName());
//        Toast.makeText(getApplicationContext(), "Showing Paired Devices",Toast.LENGTH_SHORT).show();
//
//        final ArrayAdapter adapter = new  ArrayAdapter(this,android.R.layout.simple_list_item_1, list);
//
//        //lv.setAdapter(adapter);
//    }


}

