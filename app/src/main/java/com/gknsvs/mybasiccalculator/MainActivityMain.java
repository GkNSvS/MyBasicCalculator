package com.gknsvs.mybasiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivityMain extends AppCompatActivity {

    Button btn;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_main);
        btn = findViewById(R.id.btnCounter);

        countDownTimer = new CountDownTimer(4000, 1000) {
            @Override
            public void onTick(long l) {
                btn.setText(String.valueOf(l / 1000));
            }

            @Override
            public void onFinish() {
                Intent intent = new Intent(MainActivityMain.this, MainActivity.class);
                startActivity(intent);
            }
        }.start();
        Toast.makeText(MainActivityMain.this, "Please Wait", Toast.LENGTH_LONG).show();
    }

    public void skip(View view) {
        Intent intent = new Intent(MainActivityMain.this, MainActivity.class);
        countDownTimer.cancel();
        startActivity(intent);
        finish();
    }
}