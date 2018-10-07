package com.example.youwin1234.designstuff;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.UUID;

public class QRScan extends android.support.v4.app.Fragment {
    UUID myuuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private static final String TAG = "QRScan";
    private Button scan_btn;
    BluetoothAdapter BA = BluetoothAdapter.getDefaultAdapter();

    BluetoothDevice device = null;
    BluetoothSocket tmp = null;
    BluetoothSocket mmSocket = null;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_qrscan, container, false);
        scan_btn = (Button) v.findViewById(R.id.scan_btn);
//        final Activity activity = v;
        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentIntentIntegrator integrator = new FragmentIntentIntegrator(getSelf());
                integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                integrator.setPrompt("Scan");
                integrator.setCameraId(0);
                integrator.setBeepEnabled(false);
                integrator.setBarcodeImageEnabled(false);
                integrator.initiateScan();

            }
        });
        Intent intent = new Intent(getActivity(), QRScan.class);


        //getActivity().startActivityForResult(intent, 12345);
        return v;
    }
    android.support.v4.app.Fragment getSelf(){
        return this;
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    /*
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_qrscan);

            scan_btn = (Button) findViewById(R.id.scan_btn);
            final Activity activity = this;
            scan_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    IntentIntegrator integrator = new IntentIntegrator(activity);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
                    integrator.setPrompt("Scan");
                    integrator.setCameraId(0);
                    integrator.setBeepEnabled(false);
                    integrator.setBarcodeImageEnabled(false);
                    integrator.initiateScan();

                }
            });
        }
        */
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data){
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if (result != null){
            if (result.getContents() == null){
                Toast.makeText(getContext(), "You cancelled the scanning", Toast.LENGTH_LONG).show();

            }
            else {
                Toast.makeText(getContext(), result.getContents(), Toast.LENGTH_LONG).show();
                try {
                    device = BA.getRemoteDevice(result.getContents());
                    tmp = device.createRfcommSocketToServiceRecord(myuuid);
                    try {
                        Method m = device.getClass().getMethod("createRfcommSocket", new Class[]{int.class});
                        tmp = (BluetoothSocket) m.invoke(device, 1);
                        Log.e(TAG, "CONNECTION SUCCESSFUL");

                    }
                    catch(Exception e) {
                        Log.e(TAG, "Creation Error");

                    }
                } catch (IOException e) {
                    Log.e(TAG, "create() failed", e);
                }
                mmSocket = tmp;
            }
        }
        else {

            super.onActivityResult(requestCode, requestCode, data);
        }
    }

}
