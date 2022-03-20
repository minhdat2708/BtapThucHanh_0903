package com.example.btapthuchanh_0903;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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
    DeviceDB deviceDB;

    int REQUEST_CODE_ADD = 101;
    int REQUEST_CODE_EDIT = 102;
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

        registerForContextMenu(lsvDevice);


        deviceDB = new DeviceDB(MainActivity.this);


        arrDevices = deviceDB.getAllDevices();

        Toast.makeText(this, String.valueOf(arrDevices.size()), Toast.LENGTH_SHORT).show();


//        arrDevices.add(new Devices(0, "iPhone 13", "Điện thoại", R.drawable.i_phone_13, true));
//        arrDevices.add(new Devices(1, "iPhone 13", "Điện thoại", R.drawable.i_phone_13, true));

        adapter = new DeviceAdapter(MainActivity.this, arrDevices, new onSwitch() {
            @Override
            public void onSwitchItem(Devices devices, Boolean isChecked) {
                if (isChecked) {
                    arrNoSelected.remove(devices);
                } else {
                    arrNoSelected.add(devices);
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
                                deviceDB.deleteDevice(devices.getId());
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

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddDevice.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });

        lsvDevice.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                posSelected = i;
                Toast.makeText(MainActivity.this, String.valueOf(i), Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Bundle bundle = data.getExtras();

        int id = Integer.parseInt(bundle.getString("Id"));
        int image = bundle.getInt("Image");
        String name = bundle.getString("Name");
        String des = bundle.getString("des");


        if (requestCode == REQUEST_CODE_ADD) {
            arrDevices.add(new Devices(id, name, des, image, true));
            deviceDB.addDevice(new Devices(id, name, des, image, true));
        }

        if (requestCode == REQUEST_CODE_EDIT) {

        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuInflater inflater = new MenuInflater(MainActivity.this);
        inflater.inflate(R.menu.menu_item, menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnuEdit:
                Intent intent = new Intent(MainActivity.this, AddDevice.class);
                Bundle bundle = new Bundle();
                bundle.putInt("id", arrDevices.get(posSelected).getId());
                bundle.putString("name", arrDevices.get(posSelected).getName());
                bundle.putInt("image", arrDevices.get(posSelected).getImage());
                bundle.putString("description", arrDevices.get(posSelected).getDescription());
                intent.putExtras(bundle);

                startActivityForResult(intent, REQUEST_CODE_EDIT);
//                Toast.makeText(this, "Edit Successful", Toast.LENGTH_SHORT).show();
                break;
            case R.id.mnuDelete:
                Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}