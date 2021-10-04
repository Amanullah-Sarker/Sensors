package com.amanullah.sensors;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "sensor.db";
    public static final String TABLE_NAME = "sensors_data";
    public static final String COL1 = "ID";
    public static final String COL2 = "LIGHTSENSOR";
    public static final String COL3 = "PROXIMITYSENSOR";
    public static final String COL4 = "ACCELEROMETERSENSRO";
    public static final String COL5 = "GYROSCOPESENSOR";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " LIGHTSENSOR TEXT, PROXIMITYSENSOR TEXT, ACCELEROMETERSENSRO TEXT, GYROSCOPESENSOR TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public boolean addData(String lightSensor, String proximitySensor, String accelerometerSensor, String gyroscopeSensor) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, lightSensor);
        contentValues.put(COL3, proximitySensor);
        contentValues.put(COL4, accelerometerSensor);
        contentValues.put(COL5, gyroscopeSensor);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }
}
