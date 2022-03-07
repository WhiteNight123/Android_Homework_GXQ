package com.example.lv6plus;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

public class LolFragment extends Fragment {
    private List<Hero> heroList = new ArrayList<>();

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_lol, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initHeros();
        RecyclerView recyclerView = (RecyclerView) getActivity().findViewById(R.id.recycle_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        HeroAdapter adapter = new HeroAdapter(heroList);
        recyclerView.setAdapter(adapter);
    }

    private void initHeros() {
        for (int i = 0; i < 2; i++) {
            Hero apple = new Hero("  澜属于《王者荣耀》背景故事中三分之地区域的吴阵营，身手敏捷，能以魔道之力化作鲨鱼对敌人进行暗杀。", R.drawable.lan);
            heroList.add(apple);
            Hero banana = new Hero("  云缨属于长安阵营，她身着红衣，手持长枪，攻守兼备，有一套动如掠火、千变万化的中国枪法。", R.drawable.yun_ying);
            heroList.add(banana);
            Hero orange = new Hero("夜晚的太阳，保护属于他的人。", R.drawable.chang_e);
            heroList.add(orange);
            Hero watermelon = new Hero("上官婉儿每一次攻击，都是一次酣畅淋漓的书写。每一个技能，都将去体现完全不同的书写方式，篆法·疾势将会挥出一记浑劲的笔墨，对路径以及终点的敌人造成伤害。", R.drawable.wan_er);
            heroList.add(watermelon);
            Hero pear = new Hero("我是这个世界的梦魇。", R.drawable.lv_bu);
            heroList.add(pear);
            Hero grape = new Hero("干将莫邪是一个高伤害、高收益的心流型远程狙击法师，完全依靠精准的技能施法仅仅是一个合格的干将的基础要求。当视野外或无视野的时候，沉浸在心流中的干将玩家，甩狙盲剑都会让干将使用者获得巨大的满足。以“四剑”为标签的干将，在“四剑秒”的心流产生同时会有高度的兴奋及充实感，是干将玩家的乐趣所在。", R.drawable.gan_jiang);
            heroList.add(grape);
            Hero pineapple = new Hero("容貌倾城，身段柔美。莲花是貂蝉的象征，作为一个法师，貂蝉在团战中能发挥重要的作用。", R.drawable.diao_chan);
            heroList.add(pineapple);
            Hero strawberry = new Hero("阿离是个被教坊收养的兔子魔种。即便在无比清苦的岁月中，年少的阿离也曾在心里埋下过仰慕的身影和憧憬的对象。而这些成长中的印记，潜移默化地影响着阿离，终于有一天，让她代替了那些曾经离开的人，成为长安舞台上新的焦点。也进入了她们曾经逃离的，那些传说中的位于暗面的世界。", R.drawable.a_li);
            heroList.add(strawberry);
            Hero cherry = new Hero("  苏醒了，猎杀时刻.", R.drawable.hou_yi);
            heroList.add(cherry);
            Hero mango = new Hero("人生如戏——全靠演技——", R.drawable.yuan_ge);
            heroList.add(mango);
        }

    }
}
