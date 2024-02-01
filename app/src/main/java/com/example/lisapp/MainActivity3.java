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

    private SensorManager sensorManager;
    Random rand;
    TextView di1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        rand = new Random();

    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void roll(View v) {

        for(int i = 0; i < 5 ; i++ ) {
            int randy = rand.nextInt(6) + 1;
            String s = String.valueOf(randy);
            di1 = (TextView) findViewById(R.id.di1);
            di1.setText(s);
        }
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }
    }