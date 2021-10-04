package com.amanullah.sensors;

public class User {

    private String lightSensor;
    private String proximitySensor;
    private String accelerometerSensor;
    private String gyroscopeSensor;

    public User(String lightSensor,String proximitySensor, String accelerometerSensor, String gyroscopeSensor){
        this.lightSensor = lightSensor;
        this.proximitySensor = proximitySensor;
        this.accelerometerSensor = accelerometerSensor;
        this.gyroscopeSensor = gyroscopeSensor;
    }

    public String getLightSensor() {
        return lightSensor;
    }

    public void setLightSensor(String lightSensor) {
        this.lightSensor = lightSensor;
    }

    public String getProximitySensor() {
        return proximitySensor;
    }

    public void setProximitySensor(String proximitySensor) {
        this.proximitySensor = proximitySensor;
    }

    public String getAccelerometerSensor() {
        return accelerometerSensor;
    }

    public void setAccelerometerSensor(String accelerometerSensor) {
        this.accelerometerSensor = accelerometerSensor;
    }

    public String getGyroscopeSensor() {
        return gyroscopeSensor;
    }

    public void setGyroscopeSensor(String gyroscopeSensor) {
        this.gyroscopeSensor = gyroscopeSensor;
    }
}
