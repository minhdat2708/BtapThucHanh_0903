package com.example.btapthuchanh_0903;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class AddDevice extends AppCompatActivity {

    Button btnSubAdd, btnSelectImg;
    ImageView image;
    EditText edtId, edtName, edtDes;

    int REQUEST_CODE_ADD_DEVICE = 101;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_device);

        btnSubAdd = findViewById(R.id.btnSubAdd);
        btnSelectImg = findViewById(R.id.btnSelect);
        image = findViewById(R.id.imgSubDevice);
        edtId = findViewById(R.id.edtId);
        edtName = findViewById(R.id.edtName);
        edtDes = findViewById(R.id.edtDescription);

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                try {
//                    URL url = new URL("D:\\Android\\AndroidProject\\BtapThucHanh_0903\\app\\src\\main\\res\\drawable");
//                    Bitmap bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
//                    image.setImageBitmap(bmp);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
                image.setImageDrawable(getDrawable(R.drawable.i_phone_13));
            }
        });

        btnSubAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked()) {
                    String des = edtDes.getText().toString();
                    String id = edtId.getText().toString();
                    String name = edtName.getText().toString();
                    Drawable img = image.getDrawable();

                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();

                    bundle.putString("Id", id);
                    bundle.putString("Name", name);
                    bundle.putString("des", des);

                    intent.putExtras(bundle);
                    setResult(REQUEST_CODE_ADD_DEVICE, intent);

                    finish();
                }
            }
        });
    }

    private boolean checked () {
        String id = edtId.getText().toString();
        String name = edtName.getText().toString();

        if (id.isEmpty()) {
            Toast.makeText(AddDevice.this, "Vui lòng nhập ID của sản phẩm!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (name.isEmpty()) {
            Toast.makeText(AddDevice.this, "Vui lòng nhập tên của sản phẩm!", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (image.getDrawable() == null) {
            Toast.makeText(AddDevice.this, "Vui lòng chọn ảnh của sản phẩm!", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}