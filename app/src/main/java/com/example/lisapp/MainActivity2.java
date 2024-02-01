package com.example.lisapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;

public class MainActivity2 extends AppCompatActivity implements SensorEventListener{
    private SensorManager sensorManager;
    private TextView xCoor, yCoor, zCoor;
    private float[] gravity = new float[3];
    private float alpha = 0.2f; // Adjust this value for the desired filtering strength

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        xCoor=(TextView)findViewById(R.id.xcoor);
        yCoor=(TextView)findViewById(R.id.ycoor);
        zCoor=(TextView)findViewById(R.id.zcoor);

        sensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this,
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_NORMAL); //[1]
    }

    public void home(View v){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

            gravity[0] = alpha * gravity[0] + (1 - alpha) * event.values[0];
            gravity[1] = alpha * gravity[1] + (1 - alpha) * event.values[1];
            gravity[2] = alpha * gravity[2] + (1 - alpha) * event.values[2];

            float x = event.values[0] - gravity[0];
            float y = event.values[1] - gravity[1];
            float z = event.values[2] - gravity[2];

            String xstring = String.format("%.2f",x);
            String ystring = String.format("%.2f",y);
            String zstring = String.format("%.2f",z);


            xCoor.setText("X: "+ xstring);
            yCoor.setText("Y: "+ystring);
            zCoor.setText("Z: "+zstring); //[1]
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}
}

//[1]: https://stackoverflow.com/questions/21742146/how-do-i-acquire-accelerometer-data-on-android