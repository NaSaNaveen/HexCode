package com.example.nasa.hexcodecolor;

import android.graphics.Bitmap;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Random;

public class Main2Activity extends AppCompatActivity
{
    ImageView qrcode;
    public final static int QRcodeWidth = 500 ;
    Bitmap bitmap ;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        qrcode=(ImageView)findViewById(R.id.qrcodeimg);

        try
        {
            //pass value instead of "BOW"
            bitmap = TextToImageEncode("BOW");
            qrcode.setImageBitmap(bitmap);

        }

        catch (WriterException e)
        {
            e.printStackTrace();
        }

        qrcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                Toast.makeText(Main2Activity.this, "Image Clicked", Toast.LENGTH_SHORT).show();
                String root = Environment.getExternalStorageDirectory().toString();
                File myDir = new File(root + "/Qr_code_image");
                myDir.mkdirs();
                Random generator = new Random();
                int n = 10000;
                n = generator.nextInt(n);
//                SimpleDateFormat formatter = new SimpleDateFormat("yyyy_MM_dd");
//                Date now = new Date();
//                String fileName = formatter.format(now);

                String fname = "Image-"+ n +".jpg";
                File file = new File (myDir, fname);
                if (file.exists ())
                    file.delete ();
                try
                {
                    Toast.makeText(Main2Activity.this, "Image Saved", Toast.LENGTH_SHORT).show();
                    FileOutputStream out = new FileOutputStream(file);
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
                    out.flush();
                    out.close();
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
            }
        });
    }

    Bitmap TextToImageEncode(String Value) throws WriterException
    {
        BitMatrix bitMatrix;
        try
        {
            bitMatrix = new MultiFormatWriter().encode(
                    Value,
                    BarcodeFormat.DATA_MATRIX.QR_CODE,
                    QRcodeWidth, QRcodeWidth, null
            );

        }
        catch (IllegalArgumentException Illegalargumentexception)
        {
            return null;
        }
        int bitMatrixWidth = bitMatrix.getWidth();

        int bitMatrixHeight = bitMatrix.getHeight();

        int[] pixels = new int[bitMatrixWidth * bitMatrixHeight];

        for (int y = 0; y < bitMatrixHeight; y++)
        {
            int offset = y * bitMatrixWidth;

            for (int x = 0; x < bitMatrixWidth; x++)
            {
                pixels[offset + x] = bitMatrix.get(x, y) ? getResources().getColor(R.color.QRcolorB):getResources().getColor(R.color.QRcolorW);
            }
        }
        Bitmap bitmap = Bitmap.createBitmap(bitMatrixWidth, bitMatrixHeight, Bitmap.Config.ARGB_4444);

        bitmap.setPixels(pixels, 0, QRcodeWidth, 0, 0, bitMatrixWidth, bitMatrixHeight);
        return bitmap;
    }
}
