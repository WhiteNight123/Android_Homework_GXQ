package com.example.task7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lv2 extends AppCompatActivity {

    private Handler mLv2Handler;
    public ArrayList<Lv2Data> dataArrayList = new ArrayList<>();
    private Lv2Adapter adapter;

    private class Lv2Handler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            String responseData = msg.obj.toString();
            jsonDecodeTest(responseData);
            adapter.notifyDataSetChanged(); //刷新recycleView
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv2_activity);
        Button button = (Button) findViewById(R.id.lv2_btn_start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Lv4HttpUtil.sendHttpRequest("https://www.wanandroid.com//hotkey/json", "GET", null, new Lv4HttpCallbackListener() {
                    @Override
                    public void onFinish(String response) {
                        Message message = new Message();
                        message.obj = response;
                        mLv2Handler.sendMessage(message);
                    }

                    @Override
                    public void onError(Exception e) {

                    }
                });
                Toast.makeText(Lv2.this, "请求数据已刷新", Toast.LENGTH_SHORT).show();
            }
        });

        mLv2Handler = new Lv2Handler();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv2_recyclerView);
        adapter = new Lv2Adapter(dataArrayList);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    private void jsonDecodeTest(String jsonData) {
        dataArrayList.clear(); //初始化
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(i);
                String name = data.getString("name");
                Lv2Data lv2Data = new Lv2Data();
                lv2Data.setName(name);
                dataArrayList.add(lv2Data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}