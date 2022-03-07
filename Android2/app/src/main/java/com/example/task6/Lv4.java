package com.example.task6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class Lv4 extends AppCompatActivity {
    private ArrayList<Lv4Body> bodies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv4_activity);
        initBody();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.lv4_recycle_view);
        Lv4RecycleAdapter adapter = new Lv4RecycleAdapter(bodies);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void initBody() {
        //body1被占用了
        Lv4Body body1 = new Lv4Body(R.drawable.lv4_ali, "公孙离", "有缘又有聚，才是阿离的舞台");
        Lv4Body body2 = new Lv4Body(R.drawable.lv4_ali, "公孙离", "有缘又有聚，才是阿离的舞台");
        Lv4Body body3 = new Lv4Body(R.drawable.lv4_yao, "曜", "星光荡开宇宙，本人闪耀其中");
        Lv4Body body4 = new Lv4Body(R.drawable.lv4_shouyue, "百里守约", "给我一个目标，还你一片寂静");
        Lv4Body body5 = new Lv4Body(R.drawable.lv4_houyi, "后羿", "苏醒了，猎杀时刻");
        Lv4Body body6 = new Lv4Body(R.drawable.lv4_direnjie, "狄仁杰", "代表法律制裁你");
        Lv4Body body7 = new Lv4Body(R.drawable.lv4_kai, "凯", "以绝望挥剑，着逝者为铠。");
        Lv4Body body8 = new Lv4Body(R.drawable.lv4_zhuge, "诸葛亮", "运筹帷幄之中，决胜千里之外");
        Lv4Body body9 = new Lv4Body(R.drawable.lv4_yuange, "元歌", "人生如戏，全靠演技");
        Lv4Body body10 = new Lv4Body(R.drawable.lv4_ganjiang, "干将", "千锤，百炼");
        Lv4Body body11 = new Lv4Body(R.drawable.lv4_hanxin, "韩信", "不做无法实现的梦");
        Lv4Body body12 = new Lv4Body(R.drawable.lv4_daqiao, "大乔", "空洞和孤独，依靠温暖的灯光填补。");
        bodies.add(body1);
        bodies.add(body2);
        bodies.add(body3);
        bodies.add(body4);
        bodies.add(body5);
        bodies.add(body6);
        bodies.add(body7);
        bodies.add(body8);
        bodies.add(body9);
        bodies.add(body10);
        bodies.add(body11);
        bodies.add(body12);
    }
}