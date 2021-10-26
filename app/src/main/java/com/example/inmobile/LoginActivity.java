package com.example.inmobile;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.inmobile.request.ApiClient;

public class LoginActivity extends AppCompatActivity implements SensorEventListener {
    private EditText etMail;
    private EditText etClave;
    private Button btLogin;
    private SensorManager senSensorManager;
    private Sensor senAccelerometer;
    private long lastUpdate = 0;
    private float last_x, last_y, last_z;
    private static final int SHAKE_THRESHOLD = 600;
    private String phoneNumber = "123456";
    private LoginViewModel viewModel;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        senSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        senAccelerometer = senSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        senSensorManager.registerListener(this, senAccelerometer , SensorManager.SENSOR_DELAY_NORMAL);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
                && checkSelfPermission(Manifest.permission.CALL_PHONE)
                != PackageManager.PERMISSION_GRANTED) ;

        requestPermissions(new String[]{Manifest.permission.CALL_PHONE}, 1004);


        inicializar();
    }

    private void inicializar() {
        viewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()).create(LoginViewModel.class);
        viewModel.getError().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String error) {
                Toast.makeText(getApplicationContext(), error, Toast.LENGTH_SHORT).show();
            }
        });
        viewModel.getLoginMutable().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
            }
        });
        etMail = findViewById(R.id.etMail);
        etClave = findViewById(R.id.etClave);
        btLogin = findViewById(R.id.btLogin);
        Log.d("Salida", btLogin.toString());
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(view);

            }
        });
    }
    public void login(View view) {


        String email = etMail.getText().toString();
        String clave = etClave.getText().toString();
        viewModel.autenticar(email, clave);

    }
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor mySensor = sensorEvent.sensor;

        if(mySensor.getType() == Sensor.TYPE_ACCELEROMETER){
            float x = sensorEvent.values[0];
            float y = sensorEvent.values[1];
            float z = sensorEvent.values[2];

            long curTime = System.currentTimeMillis();
            if ((curTime - lastUpdate) > 100){
                long diffTime = (curTime - lastUpdate);
                lastUpdate = curTime;

                float move = Math.abs(x + y + z - last_x - last_y - last_z)/ diffTime * 10000;

                if (move > SHAKE_THRESHOLD) {
                    call();
                }

                last_x = x;
                last_y = y;
                last_z = z;
            }
        }

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }

    @Override
    protected void onPause() {
        super.onPause();
        //anular el registro del sensor
        senSensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        //registrar nuevamente cuando se ejecute una reanudacion de la aplicacion
        senSensorManager.registerListener(this,senAccelerometer,SensorManager.SENSOR_DELAY_NORMAL);

    }

    public void  call(){

        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel: " + phoneNumber));
        startActivity(intent);
    }


}
