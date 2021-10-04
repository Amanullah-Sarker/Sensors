package com.amanullah.sensors;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FourColumnListAdapter extends ArrayAdapter {

    private LayoutInflater mInflater;
    private ArrayList<User> users;
    private int mViewResourceId;

    public FourColumnListAdapter(Context context, int textViewResourceId, ArrayList<User> users) {
        super(context, textViewResourceId, users);
        this.users = users;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        User user = users.get(position);

        if (user != null) {
            TextView lightSensorShow = (TextView) convertView.findViewById(R.id.lightSensorTextView);
            TextView proximitySensorShow = (TextView) convertView.findViewById(R.id.proximitySensorTextView);
            TextView accelerometerSensorShow = (TextView) convertView.findViewById(R.id.accelerometerSensorTextView);
            TextView gyroscopeSensorShow = (TextView) convertView.findViewById(R.id.gyroscopeSensorTextView);
            if (lightSensorShow != null) {
                lightSensorShow.setText((user.getLightSensor()));
            }if (proximitySensorShow != null) {
                proximitySensorShow.setText((user.getProximitySensor()));
            }if (accelerometerSensorShow != null) {
                accelerometerSensorShow.setText((user.getAccelerometerSensor()));
            }if (gyroscopeSensorShow != null) {
                gyroscopeSensorShow.setText((user.getGyroscopeSensor()));
            }
        }

        return convertView;
    }

}
