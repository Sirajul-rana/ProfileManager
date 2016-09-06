package com.my.profilemanager;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileManagerActivity extends AppCompatActivity implements View.OnClickListener {


    private Button startservice;
    private Button stopservice;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_manager);

        startservice=(Button)findViewById(R.id.startservice);
        stopservice=(Button)findViewById(R.id.stopservice);

        intent = new Intent(ProfileManagerActivity.this, SensorService.class);

        startservice.setOnClickListener(this);
        stopservice.setOnClickListener(this);
        Log.d("ProfileManagerActivity", "Activity Started");
    }

    protected void onResume() {
        super.onResume();

    }

    protected void onPause() {
        super.onPause();

    }

    @Override
    public void onClick(View v) {
        try {
            Button button = (Button)v;
            if(button==startservice)
            {
                startService(new Intent(this, SensorService.class));
            }
            else if (button==stopservice)
            {
                stopService(new Intent(this, SensorService.class));
            }
        }
        catch (Exception e)
        {
            Toast.makeText(ProfileManagerActivity.this, e.toString(), Toast.LENGTH_SHORT).show();
        }
    }


}
