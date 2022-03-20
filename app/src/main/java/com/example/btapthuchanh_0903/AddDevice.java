package com.example.btapthuchanh_0903;

import androidx.annotation.Nullable;
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

    int img;

    int REQUEST_CODE_ADD_DEVICE = 101;
    int REQUEST_CODE_EDIT_DEVICE = 101;
    int REQUEST_CODE_SELECT_IMG = 201;


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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            int id = bundle.getInt("id");
            String name = bundle.getString("name");
            String description = bundle.getString("description");
            int image1 = bundle.getInt("image");

            edtId.setText(String.valueOf(id));
            edtName.setText(name);
            edtDes.setText(description);
            image.setImageResource(image1);
            btnSubAdd.setText("Chỉnh sửa");
        }

        btnSelectImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AddDevice.this, ImageDevice.class);
                startActivityForResult(intent, REQUEST_CODE_SELECT_IMG);
            }
        });

        btnSubAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checked()) {
                    String des = edtDes.getText().toString();
                    String id = edtId.getText().toString();
                    String name = edtName.getText().toString();

                    Intent intent = getIntent();
                    Bundle bundle = new Bundle();

                    bundle.putString("Id", id);
                    bundle.putString("Name", name);
                    bundle.putString("des", des);
                    bundle.putInt("Image", img);

                    intent.putExtras(bundle);
                    setResult(REQUEST_CODE_ADD_DEVICE, intent);

                    if (btnSubAdd.getText() == "Edit") {
                        setResult(REQUEST_CODE_EDIT_DEVICE, intent);
                    }

                    finish();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (requestCode ==  201 && resultCode == RESULT_OK && data != null) {
            int imageResult = data.getIntExtra("image", R.drawable.notfound);
            img = imageResult;
            image.setImageResource(img);
        }

        super.onActivityResult(requestCode, resultCode, data);
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