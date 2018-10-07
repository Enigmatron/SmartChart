package com.example.youwin1234.designstuff;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.support.v4.app.Fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.Set;

public class QRCodeGenFragment extends android.support.v4.app.Fragment {

    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;
    String code;




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
    public void onDestroy() {
        super.onDestroy();


        // Don't forget to unregister the ACTION_FOUND receiver.
        getActivity().unregisterReceiver(mReceiver);
    }

    public void on(View v) {
//        Toast.makeText(getApplicationContext(), "Turned on",Toast.LENGTH_LONG).show();
        if (BA == null) {
            Log.d("Bluetooth", " AdapterNull");

            Toast.makeText(getContext(), "BA Null", Toast.LENGTH_LONG).show();
        }
        else {
            if (!BA.isEnabled()) {
                Log.d("Bluetooth", "Adapter is not Enabled");

                Toast.makeText(getContext(), "BA Enabled", Toast.LENGTH_LONG).show();
                Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivity(enableIntent);
                IntentFilter BIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                getActivity().registerReceiver(mReceiver, BIntent);

                //TODO: Is enabled continue with functionality for enabled devices...
            }
            if (BA.isEnabled()) {
                Log.d("Bluetooth", "Adapter is Enabled");

                Toast.makeText(getContext(), "BA Disable", Toast.LENGTH_LONG).show();

                BA.disable();
                IntentFilter BIntent = new IntentFilter(BluetoothAdapter.ACTION_STATE_CHANGED);
                getActivity().registerReceiver(mReceiver, BIntent);
            }
        }
    }



    private EditText etInput;
    private Button btnCreateQr;
    private ImageView imageView;


//    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.activity_qrcode_gen, container, false);
//        context = getActivity
        etInput = (EditText) v.findViewById(R.id.etInput);
        btnCreateQr = (Button) v.findViewById(R.id.btnCreate);
        imageView = (ImageView) v.findViewById(R.id.imageView);
        BA = BluetoothAdapter.getDefaultAdapter();
        code = BA.getAddress();
//            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//            startActivityForResult(getVisible,0);
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        getActivity().registerReceiver(mReceiver, filter);


        btnCreateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString().trim();

                if(code != null){
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(code, BarcodeFormat.QR_CODE,
                                1000, 1000);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });



        return v;

    }

//    @Override
//    protected void onViewCreate(View view, Bundle savedInstancesState) {
//        super.onCreate(savedInstancesState);
////        view.setContentView(R.layout.activity_qrcode_gen);
//
//        etInput = (EditText) view.findViewById(R.id.etInput);
//        btnCreateQr = (Button) view.findViewById(R.id.btnCreate);
//        imageView = (ImageView) view.findViewById(R.id.imageView);
//    }


    /*
public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
{
    etInput = getActivity().findViewById(R.id.etInput);
    btnCreateQr = getView().findViewById(R.id.btnCreate);
    imageView = getView().findViewById(R.id.imageView);

    btnCreateQr.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String text = etInput.getText().toString().trim();

            if(text != null){
                MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                try {
                    BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,
                            1000, 1000);
                    BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                    Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                    imageView.setImageBitmap(bitmap);
                } catch (WriterException e) {
                    e.printStackTrace();
                }
            }
        }
    });
}
*?
    /*
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrcode_gen);

        etInput = getView().findViewById(R.id.etInput);
        btnCreateQr = findViewById(R.id.btnCreate);
        imageView = findViewById(R.id.imageView);

        btnCreateQr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = etInput.getText().toString().trim();

                if(text != null){
                    MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

                    try {
                        BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,
                                1000, 1000);
                        BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
                        Bitmap bitmap = barcodeEncoder.createBitmap(bitMatrix);
                        imageView.setImageBitmap(bitmap);
                    } catch (WriterException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }
    */
}

