package com.example.hannaandreason.myfirstapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Accelerometer extends AppCompatActivity implements SensorEventListener {

    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private TextView acceleration;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accelerometer);


        //Accelerometer
        //Added from sensorManager-turtorial
       // Get an instance of this class
            mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
            mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);

            acceleration=(TextView) findViewById(R.id.accelerator);
        }


        //No changes
        protected void onPause() {
            super.onPause();
            mSensorManager.unregisterListener(this);
        }

        //Added from SensorEvent-turtorial
        public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) { //Taken from https://www.built.io/blog/applying-low-pass-filter-to-android-sensor-s-readings
                // calculate the position of the phone.

                acceleration.setText("X: " + event.values[0] +
                        "\nY: " + event.values[1] +
                        "\nZ: " + event.values[2]);
            }
        }

        //Not needed
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
}
