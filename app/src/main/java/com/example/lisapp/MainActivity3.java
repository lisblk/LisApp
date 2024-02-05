package com.example.lisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.sql.SQLOutput;
import java.util.Random;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {

    private static final float SHAKE_THRESHOLD = 5.0f; // Adjust this value as needed
    private static final int SHAKE_TIME_INTERVAL = 100; // Adjust this value as needed

    private SensorManager sensorManager;
    Random rand;
    TextView di1;
    private long lastShakeTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rand = new Random();

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if (accelerometerSensor != null) {
            sensorManager.registerListener(this, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void roll() {
            int randy = rand.nextInt(6) + 1;
            String s = String.valueOf(randy);
            di1 = (TextView) findViewById(R.id.di1);
            di1.setText(s);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            long currentTime = System.currentTimeMillis();

            if ((currentTime - lastShakeTime) > SHAKE_TIME_INTERVAL) {
                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

                if (acceleration > SHAKE_THRESHOLD) {
                    lastShakeTime = currentTime;
                    roll();
                }
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    public void fortuneTeller(int i){
        switch (i)
            case
    }


    }

