package com.example.btapthuchanh_0903;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtName;
    TextView txtDescription;
    EditText edtName;
    ListView lsvDevice;
    Switch swStatus;
    Button btnAdd, btnDelete;
    ArrayList<Devices> arrDevices;
    ArrayList<Devices> arrNoSelected;
    DeviceAdapter adapter;

    int posSelected = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtName = findViewById(R.id.edtName);
        lsvDevice = findViewById(R.id.lsvDevice);
        swStatus = findViewById(R.id.swStatus);
        btnDelete = findViewById(R.id.btnDelete);
        btnAdd = findViewById(R.id.btnAdd);
        txtName = findViewById(R.id.txtName);
        txtDescription = findViewById(R.id.txtDescription);

        arrDevices = new ArrayList<>();
        arrNoSelected = new ArrayList<>();

        arrDevices.add(new Devices(0, "iPhone 13", "Điện thoại", R.drawable.i_phone_13, false));
        arrDevices.add(new Devices(1, "iPhone 13", "Điện thoại", R.drawable.i_phone_13, false));

        adapter = new DeviceAdapter(MainActivity.this, arrDevices, new onSwitch() {
            @Override
            public void onSwitchItem(Devices devices, Boolean isChecked) {
                if (!isChecked) {
                    arrNoSelected.add(devices);
                } else {
                    arrNoSelected.remove(devices);
                }
            }
        });

        lsvDevice.setAdapter(adapter);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (arrNoSelected.size() > 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Xoá thiết bị");
                    builder.setMessage("Bạn có chắc chắn muốn xoá thiết bị?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            for (Devices devices:arrNoSelected) {
                                arrDevices.remove(devices);
                                adapter.notifyDataSetChanged();
                            }
                        }
                    });
                    builder.setNegativeButton(("Không"), new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                        }
                    });
                    builder.create();
                    builder.show();
                } else {
                    Toast.makeText(MainActivity.this, "Tất cả thiết bị đang bận, không thể xoá!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}