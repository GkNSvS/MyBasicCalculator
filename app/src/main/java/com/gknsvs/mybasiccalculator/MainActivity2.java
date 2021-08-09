package com.gknsvs.mybasiccalculator;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {

    TextView[] t = new TextView[5];
    TextView txttime;
    long time;
    Handler handler;
    Runnable runnable;
    SharedPreferences shared;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        shared = this.getSharedPreferences("com.gknsvs.basiccalculater", Context.MODE_PRIVATE);
        t[0] = findViewById(R.id.textHistory1);
        t[1] = findViewById(R.id.textHistory2);
        t[2] = findViewById(R.id.textHistory3);
        t[3] = findViewById(R.id.textHistory4);
        t[4] = findViewById(R.id.textHistory5);
        showScreen();
        txttime = findViewById(R.id.txtTime2);
        Intent intent = getIntent();
        String res = intent.getStringExtra("lastResult");
        time = intent.getLongExtra("time", 0);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                time++;
                txttime.setText(String.valueOf(time) + " sn");
                handler.postDelayed(runnable, 1000);
            }
        };
        handler.post(runnable);
        addResault(res);
    }

    private void showScreen() {
        t[0].setText(shared.getString("res0", null));
        t[1].setText(shared.getString("res1", null));
        t[2].setText(shared.getString("res2", null));
        t[3].setText(shared.getString("res3", null));
        t[4].setText(shared.getString("res4", null));

    }

    private void addResault(String res) {
        int count = shared.getInt("count", 0);
        if (count < 5) {
            t[count].setText(res);
            shared.edit().putString("res" + count, res).apply();
            shared.edit().putInt("count", ++count).apply();
        } else {
            deleteresalult(res);
        }
    }

    private void deleteresalult(String res) {
        for (int i = 0; i < 4; i++) {
            shared.edit().putString("res" + i, t[i + 1].getText().toString()).apply();
            t[i].setText(shared.getString("res" + i, null));
        }
        shared.edit().putString("res4", res).apply();
        t[4].setText(res);
    }

    public void stop(View view) {

    }

    public void back(View view) {
        handler.removeCallbacks(runnable);
        Intent intent = new Intent(MainActivity2.this, MainActivity.class);
        intent.putExtra("time", time);
        startActivity(intent);
        finish();
    }

    public void clear(View view) {
        shared.edit().clear().apply();
        showScreen();
    }
}