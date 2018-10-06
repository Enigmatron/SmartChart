package com.example.youwin1234.designstuff;
import android.graphics.Bitmap;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class QRCodeGenFragment extends android.support.v4.app.Fragment {

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

