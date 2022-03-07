package com.example.task7;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class Lv5 extends AppCompatActivity {
    private ArrayList<Lv5Data> dataArrayList = new ArrayList<>();
    private ViewPager2 viewPager2;
    public Lv5PagerAdapter adapter;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            jsonDecodeTest(msg.obj.toString());
            adapter.notifyDataSetChanged();
            return false;
        }
    });

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv5_activity);
        initData();
        viewPager2 = findViewById(R.id.lv5_vp2);
        adapter = new Lv5PagerAdapter(getApplicationContext(), dataArrayList);
        viewPager2.setAdapter(adapter);
    }

    public void initData() {
        Lv4HttpUtil.sendHttpRequest("https://www.wanandroid.com/banner/json", "GET", null, new Lv4HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message msg = new Message();
                msg.what = 1;
                msg.obj = response;
                handler.sendMessage(msg);
            }

            @Override
            public void onError(Exception e) {

            }
        });
    }

    private void jsonDecodeTest(String jsonData) {
        dataArrayList.clear(); //初始化
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray jsonArray = jsonObject.getJSONArray("data");
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject data = jsonArray.getJSONObject(i);
                String title = data.getString("title");
                String imagePath = data.getString("imagePath");
                Lv5Data lv5Data = new Lv5Data();
                lv5Data.setTitle(title);
                lv5Data.setImageUrl(imagePath);
                dataArrayList.add(lv5Data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}