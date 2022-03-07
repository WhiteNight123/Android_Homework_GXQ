package com.example.task7;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {
    public Button mButton2;
    public Button mButton3;
    public Button mButton4;
    public Button mButton5;
    public Button mButton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        mButton2.setOnClickListener(this);
        mButton5.setOnClickListener(this);
        mButton6.setOnClickListener(this);
    }

    public void init() {
        mButton2 = (Button) findViewById(R.id.button_lv2);
        mButton5 = (Button) findViewById(R.id.button_lv5);
        mButton6 = (Button) findViewById(R.id.button_lv6);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_lv2:
                Intent intent2 = new Intent(StartActivity.this, Lv2.class);
                startActivity(intent2);
                break;
            case R.id.button_lv5:
                Intent intent5 = new Intent(StartActivity.this, Lv5.class);
                startActivity(intent5);
                break;
            case R.id.button_lv6:
                Intent intent6 = new Intent(StartActivity.this, Lv6.class);
                startActivity(intent6);
                break;
            default:
                break;
        }
    }
}