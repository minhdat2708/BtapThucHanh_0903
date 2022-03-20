package com.example.btapthuchanh_0903;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DeviceDB extends SQLiteOpenHelper {
    public static final String DB_NAME = "Database";
    // Tên bảng
    public static final String TableName = "DeviceTable";
    // Tên các cột trong bảng
    public static final String Id = "Id";
    public static final String Name = "Name";
    public static final String Description = "Description";
    public static final String Img = "Image";
    public static final String Status = "Status";

    public DeviceDB(Context context) {
        super(context, DB_NAME, null, 2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlCreate = "Create table if not Exists " + TableName + "("
                + Id + " Interger Primary key, "
                + Name + " Text, "
                + Description + " Text, "
                + Img + " Integer, "
                + Status + " Integer)";
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("Drop table if exists " + TableName);
        onCreate(db);
    }

    public void addDevice(Devices devices) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Id, devices.getId());
        values.put(Name, devices.getName());
        values.put(Description, devices.getDescription());
        values.put(Img, devices.getImage());
        values.put(Status, devices.isStatus() ? 1 : 0);
        db.insert(TableName, null, values);
        db.close();
    }

    public void updateDevice(int id, Devices devices) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues value = new ContentValues();
        value.put(Id, devices.getId());
        value.put(Name, devices.getName());
        value.put(Description, devices.getDescription());
        value.put(Img, devices.getImage());
        value.put(Status, devices.isStatus());
        db.update(TableName, value, Id + "=?", new String[]{String.valueOf(id)});
    }

    public void deleteDevice(int id) {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "Delete From " + TableName + " Where ID = " + id;
        db.execSQL(sql);
    }

    public ArrayList<Devices> getAllDevices () {
        ArrayList<Devices> list = new ArrayList<>();

        String sql = "Select * from " + TableName;

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(sql, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                Devices devices = new Devices(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2),
                        cursor.getInt(3),
                        cursor.getInt(4) == 1 ? true : false);
                list.add(devices);
            }
        }
        return list;
    }
}
