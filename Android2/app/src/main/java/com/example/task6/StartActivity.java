package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mButtonLv1;
    private Button mButtonLv2;
    private Button mButtonLv3;
    private Button mButtonLv4;
    private Button mButtonLv5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        init();
        mButtonLv1.setOnClickListener(this);
        mButtonLv2.setOnClickListener(this);
        mButtonLv3.setOnClickListener(this);
        mButtonLv4.setOnClickListener(this);
        mButtonLv5.setOnClickListener(this);

    }

    public void init() {
        mButtonLv1 = (Button) findViewById(R.id.btn_start_lv1);
        mButtonLv2 = (Button) findViewById(R.id.btn_start_lv2);
        mButtonLv3 = (Button) findViewById(R.id.btn_start_lv3);
        mButtonLv4 = (Button) findViewById(R.id.btn_start_lv4);
        mButtonLv5 = (Button) findViewById(R.id.btn_start_lv5);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start_lv1:
                Intent intent1 = new Intent(StartActivity.this, Lv1.class);
                startActivity(intent1);
                break;
            case R.id.btn_start_lv2:
                Intent intent2 = new Intent(StartActivity.this, Lv2.class);
                startActivity(intent2);
                break;
            case R.id.btn_start_lv3:
                Intent intent3 = new Intent(StartActivity.this, Lv3.class);
                startActivity(intent3);
                break;
            case R.id.btn_start_lv4:
                Intent intent4 = new Intent(StartActivity.this, Lv4.class);
                startActivity(intent4);
                break;
            case R.id.btn_start_lv5:
                Intent intent5 = new Intent(StartActivity.this, Lv5.class);
                startActivity(intent5);
                break;
        }
    }
}