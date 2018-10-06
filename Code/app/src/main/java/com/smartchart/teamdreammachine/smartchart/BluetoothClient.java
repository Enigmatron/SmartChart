package com.smartchart.teamdreammachine.smartchart;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

//import com.google.android.things.bluetooth.BluetoothConnectionManager;

import java.util.Set;

public class BluetoothClient extends AppCompatActivity {

        private BluetoothAdapter BA;
        private Set<BluetoothDevice> pairedDevices;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_bluetooth_client);
            BA = BluetoothAdapter.getDefaultAdapter();

//            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//            startActivityForResult(getVisible,0);
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            registerReceiver(mReceiver, filter);
        }



    // Create a BroadcastReceiver for ACTION_FOUND.
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            if(action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)){
                final int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BA.ERROR);
                switch(state){
                    case BluetoothAdapter.STATE_ON:
                        Log.d("Bluetooth", "STATE_ON");
                        break;
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Log.d("Bluetooth", "STATE_TURNING_ON");

                        break;
                    case BluetoothAdapter.STATE_OFF:
                        Log.d("Bluetooth", "STATE_OFF");

                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Log.d("Bluetooth", "STATE_TURNING_OFF");

                        break;
                }
            }
//            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
//                // Discovery has found a device. Get the BluetoothDevice
//                // object and its info from the Intent.
////                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
////                String deviceName = device.getName();
////                String deviceHardwareAddress = device.getAddress(); // MAC address
//            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();


        // Don't forget to unregister the ACTION_FOUND receiver.
        unregisterReceiver(mReceiver);
    }

    public void on(View v) {
//        Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        if (BA == null) {
            Log.d("Bluetooth", " AdapterNull");

            Toast.makeText(getApplicationContext(), "BA Null", Toast.LENGTH_LONG).show();
        }
        else {
            if (!BA.isEnabled()) {
                Log.d("Bluetooth", "Adapter is not Enabled");

                Toast.makeText(getApplicationContext(), "BA Enabled", Toast.LENGTH_LONG).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableIntent);
                IntentFilter BIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                registerReceiver(mReceiver, BIntent);

                //TODO: Is enabled continue with functionality for enabled devices...
            }
            if (BA.isEnabled()) {
                Log.d("Bluetooth", "Adapter is Enabled");

                Toast.makeText(getApplicationContext(), "BA Disable", Toast.LENGTH_LONG).show();

                BA.disable();
                IntentFilter BIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                registerReceiver(mReceiver, BIntent);
            }
        }
    }
//        else{
//            //TODO: Explicitly ask user to enable bluetooth device
//            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
//            System.out.println("attempt to turn on");
//            startActivityForResult(new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE), 0);
//        }

//        if (!BA.isEnabled()) {
//            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//            startActivityForResult(turnOn, 0);
//            Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
//        } else {
//            Toast.makeText(getApplicationContext(), "Already on", Toast.LENGTH_LONG).show();
//        }
//    }
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

