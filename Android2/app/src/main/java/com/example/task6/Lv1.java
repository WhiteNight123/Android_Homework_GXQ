package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class Lv1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv1_activity);
    }
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.layout_lv1:
                Toast.makeText(Lv1.this, "你点击了按钮", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}