package com.example.lisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import java.util.Random;
import android.os.Vibrator;

public class MainActivity3 extends AppCompatActivity implements SensorEventListener {
    private static final float SHAKE_THRESHOLD = 5.0f;
    private SensorManager sensorManager;
    Random rand;
    TextView di1;
    Vibrator vibrator;

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

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    public void roll() {
            di1 = (TextView) findViewById(R.id.di1);

        di1.setText(".");

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            di1.setText("..");

            handler.postDelayed(() -> {
                di1.setText("...");

                handler.postDelayed(() -> {
                    di1.setText("");
                    di1.setText(fortuneTeller());
                    vibrator.vibrate(100);
                }, 700);
            }, 700);
        }, 700);

        onResume();
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                float x = event.values[0];
                float y = event.values[1];
                float z = event.values[2];

                double acceleration = Math.sqrt(x * x + y * y + z * z) - SensorManager.GRAVITY_EARTH;

                if (acceleration > SHAKE_THRESHOLD) {
                    onPause();
                    roll();
                }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    public void onPause(){
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    public void onResume(){
        super.onResume();
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
    }

    public String fortuneTeller(){
        int randy = rand.nextInt(10);
        switch (randy) {
            case 0:
                return "Aldrig";
            case 1:
                return "Kanske";
            case 2:
                return "Nej";
            case 3:
                return "Glöm det";
            case 4:
                return "100%";
            case 5:
                return "Definitivt";
            case 6:
                return "Inte nu";
            case 7:
                return "JAAAA";
            case 8:
                return "Aa, faktiskt";
            case 9:
                return "På egen risk";
            default:
                return "testa igen";

            }
        }
    }

