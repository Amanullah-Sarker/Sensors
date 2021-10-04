package com.amanullah.sensors;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    private TextView textViewLightSensor, textViewProximitySensor, textViewAccelerometerSensor, textViewGyroscopeSensor;

    private SensorManager sensorManager;

    private Sensor lightSensor, proximitySensor, accelerometerSensor, gyroscopeSensor;

    DatabaseHelper mDatabaseHelper;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //getting the id of Textview
        textViewLightSensor = findViewById(R.id.textViewLightSensorId);
        textViewProximitySensor = findViewById(R.id.textViewProximitySensorId);
        textViewAccelerometerSensor = findViewById(R.id.textViewAccelerometerSensorId);
        textViewGyroscopeSensor = findViewById(R.id.textViewGyroscopeSensorId);

        //initiate the sensor manager
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        //initiate the sensor
        lightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        proximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        gyroscopeSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        mDatabaseHelper = new DatabaseHelper(this);
    }

    @Override
    protected void onDestroy() {
        Intent intent = new Intent(this, ExampleService.class);
        ContextCompat.startForegroundService(this, intent);
        Toast.makeText(getApplicationContext(), "Background is Running", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, ExampleService.class);
        ContextCompat.startForegroundService(this, intent);
        super.onBackPressed();

        Toast.makeText(getApplicationContext(), "Background is Running", Toast.LENGTH_SHORT).show();
    }

    public void stopService(View v){
        Intent intent = new Intent(this, ExampleService.class);
        stopService(intent);

        Toast.makeText(getApplicationContext(), "Background is Stop", Toast.LENGTH_SHORT).show();
    }

    public void saveData(View v){
        String lightSensorValue = textViewLightSensor.getText().toString();
        String proximitySensorValue = textViewProximitySensor.getText().toString();
        String accelerometerSensorValue = textViewAccelerometerSensor.getText().toString();
        String gyroscopeSensorValue = textViewGyroscopeSensor.getText().toString();

        AddData(lightSensorValue, proximitySensorValue, accelerometerSensorValue, gyroscopeSensorValue);
    }

    public void lightClick(View v){
        Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
        finish();
    }

    public void proximityClick(View V){
        Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
        finish();
    }

    public void accelerometerClick(View v){
        Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
        finish();
    }

    public void gyroscopeClick(View v){
        Intent intent = new Intent(getApplicationContext(), ListDataActivity.class);
        startActivity(intent);
        finish();
    }

    private void AddData(String lightSensor, String proximitySensor, String accelerometerSensor, String gyroscopeSensor){

        boolean insertData = mDatabaseHelper.addData(lightSensor, proximitySensor, accelerometerSensor, gyroscopeSensor);

        if (insertData){
            Toast.makeText(getApplicationContext(), "Successful", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onPause() {
        sensorManager.unregisterListener(this);

        Intent intent = new Intent(this, ExampleService.class);
        ContextCompat.startForegroundService(this, intent);

        Toast.makeText(getApplicationContext(), "Background is Running", Toast.LENGTH_SHORT).show();

        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();

        sensorManager.registerListener(MainActivity.this, lightSensor, sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, proximitySensor, sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, accelerometerSensor, sensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(MainActivity.this, gyroscopeSensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == Sensor.TYPE_LIGHT){

            double lightValue = (double) event.values[0];

            textViewLightSensor.setText("Light Sensor = " + lightValue);

        }else if (event.sensor.getType() == Sensor.TYPE_PROXIMITY){

            double proximityValue = (double) event.values[0];

            textViewProximitySensor.setText("Proximity Sensor = " + proximityValue);

        }else if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){

            double accelerometerValue = (double) event.values[0];
            //double accelerometerY = (double) event.values[1];
            //double accelerometerZ = (double) event.values[2];

            //textViewAccelerometerSensor.setText("Accelerometer Sensor: \n" + "X Axis = " + accelerometerX + "\nY Axis = " + accelerometerY + "\nZ Axis = " + accelerometerZ);
            textViewAccelerometerSensor.setText("Accelerometer Sensor: " + accelerometerValue);

        }else if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE){
            if (gyroscopeSensor == null){

                Toast.makeText(getApplicationContext(), "This Devie has No Gyrosope", Toast.LENGTH_SHORT).show();

            } else {

                double gyroscopeValue = (double) event.values[0];

                textViewGyroscopeSensor.setText("Gyroscope Sensor = " + gyroscopeValue);

            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }
}