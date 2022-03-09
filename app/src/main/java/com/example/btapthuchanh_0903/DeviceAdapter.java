package com.example.btapthuchanh_0903;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class DeviceAdapter extends BaseAdapter implements onSwitch{

    private Activity activity;
    private ArrayList<Devices> lstDevices;
    private LayoutInflater inflater;

    onSwitch selected;

    public DeviceAdapter(Activity activity, ArrayList<Devices> lstDevices,onSwitch selected) {
        this.activity = activity;
        this.lstDevices = lstDevices;
        this.inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.selected = selected;
    }

    @Override
    public int getCount() {
        return lstDevices.size();
    }

    @Override
    public Devices getItem(int i) {
        return lstDevices.get(i);
    }

    @Override
    public long getItemId(int i) {
        Devices devices = lstDevices.get(i);
        return devices.getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if (view == null) {
            view = inflater.inflate(R.layout.list_device, null);
        }

        Devices object = getItem(i);

        if (object != null) {
            Switch swStatus = view.findViewById(R.id.swStatus);
            TextView txtName = view.findViewById(R.id.txtName);
            TextView txtDescription = view.findViewById(R.id.txtDescription);
            ImageView imgDevice = view.findViewById(R.id.imgDevice);

            txtName.setText(object.getName());
            txtDescription.setText(object.getDescription());
            swStatus.setChecked(object.isStatus());
            imgDevice.setImageResource(object.getImage());

            swStatus.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    selected.onSwitchItem(object, b);
                }
            });
        }

        return view;
    }
}
