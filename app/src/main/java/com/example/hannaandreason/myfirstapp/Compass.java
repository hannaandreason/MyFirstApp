package com.example.hannaandreason.myfirstapp;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class Compass extends AppCompatActivity implements SensorEventListener{

    ImageView pointer;
    TextView degrees;

    private SensorManager mSensorManager;
    private Sensor mCompass;

    private float currentDegree= 0f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);

        pointer= (ImageView) findViewById(R.id.pointer);
        degrees=(TextView) findViewById(R.id.text_degrees);


        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mCompass = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
        mSensorManager.registerListener(this, mCompass, SensorManager.SENSOR_DELAY_NORMAL);
    }


    @Override
    protected void onResume() {
        super.onResume();

        if(mCompass !=  null) {
            mSensorManager.registerListener(this, mCompass, SensorManager.SENSOR_DELAY_FASTEST);
        }else{
            Toast.makeText(Compass.this, "This is not supported", Toast.LENGTH_SHORT).show();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();

        mSensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        int degree=Math.round(event.values[0]);

        degrees.setText(Integer.toString(degree) + (char) 0x00B0);

        RotateAnimation pointer_rotate = new RotateAnimation( currentDegree, -degree, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);

        pointer_rotate.setDuration(1000);
        pointer_rotate.setFillAfter(true);

        pointer.startAnimation(pointer_rotate);
        currentDegree = -degree;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    // Not needed
    }
}
