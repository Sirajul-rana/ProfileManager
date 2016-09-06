package com.my.profilemanager;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.os.IBinder;
import android.os.Vibrator;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.EditText;

public class SensorService extends Service implements SensorEventListener {

    private AudioManager audioManager;
    private AudioManager audioManager1;
    private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor mProximity;
    private Sensor mLight;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;



    @Override
    public void onCreate() {
        super.onCreate();
        mSensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        mAccelerometer = mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mProximity = mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mLight = mSensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        audioManager = (AudioManager)getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        audioManager1 = (AudioManager)getBaseContext().getSystemService(Context.AUDIO_SERVICE);
        telephonyManager = (TelephonyManager) getSystemService(TELEPHONY_SERVICE);
        phoneStateListener = new PhoneStateListener();
        Log.d("Sensor Service", "Service Started");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("Sensor Service", "Service Destroyed");
        mSensorManager.unregisterListener(this);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("MSG", "Service Started");
        mSensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        mSensorManager.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL);
        return super.onStartCommand(intent, flags, startId);
    }


    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;


        if (telephonyManager.getCallState()== telephonyManager.CALL_STATE_RINGING && sensor.getType()== Sensor.TYPE_PROXIMITY) {
            if (event.values[0]==0)
            {
                Log.d("MSG", "OnSilentMode in on");
                audioManager.setRingerMode(AudioManager.RINGER_MODE_SILENT);
            }
            else
            {
                if (telephonyManager.getCallState()== telephonyManager.CALL_STATE_RINGING)
                Log.d("MSG", "OnSilentMode in off");
                audioManager1.setRingerMode(AudioManager.RINGER_MODE_NORMAL);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
