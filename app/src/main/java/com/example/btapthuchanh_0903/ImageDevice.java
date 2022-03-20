package com.example.btapthuchanh_0903;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;

public class ImageDevice extends AppCompatActivity {

    GridView gvImage;
    ArrayList<Image> images;
    int REQUEST_CODE_SELECT_IMG = 201;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_device);

        gvImage = findViewById(R.id.gvImageDevice);
        images = new ArrayList<>();
        images.add(new Image("Product 1", R.drawable.product1, "1"));
        images.add(new Image("Product 2", R.drawable.product2, "2"));
        images.add(new Image("Product 3", R.drawable.product3, "3"));
        images.add(new Image("Product 4", R.drawable.product4, "4"));
        images.add(new Image("Product 5", R.drawable.product5, "5"));
        images.add(new Image("Product 6", R.drawable.product6, "6"));
        images.add(new Image("Product 7", R.drawable.product7, "7"));
        images.add(new Image("Product 8", R.drawable.product8, "8"));
        images.add(new Image("Product 9", R.drawable.product9, "9"));
        images.add(new Image("Product 10", R.drawable.i_phone_13, "10"));

        ImageAdapter adapter = new ImageAdapter(ImageDevice.this, R.layout.image_line, images);

        gvImage.setAdapter(adapter);

        gvImage.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int img = images.get(i).getImage();
                Intent intent = new Intent();
                intent.putExtra("image", img);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

    }
}