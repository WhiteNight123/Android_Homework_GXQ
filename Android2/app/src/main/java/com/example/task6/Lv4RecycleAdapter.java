package com.example.task6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Lv4RecycleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<Lv4Body> data;
    final int HOLDER_NUMBER = 2;

    public Lv4RecycleAdapter(ArrayList<Lv4Body> data) {
        this.data = data;
    }

    //根据不同的ViewHolder来加载不同的视图
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return new Lv4HeaderHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv4_activity_item_rv_header, parent, false));
        } else {
            return new Lv4BodyHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.lv4_activity_item_rv_body, parent, false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof Lv4HeaderHolder) {
            ((Lv4HeaderHolder) holder).setData();
        } else if (holder instanceof Lv4BodyHolder) {
            ((Lv4BodyHolder) holder).setData(data, position);
        }
    }

    @Override
    public int getItemCount() {
        return data.size();   //标题占用了一个
    }

    @Override
    public int getItemViewType(int position) {
        return Math.min(position, HOLDER_NUMBER - 1);
    }
}

class Lv4HeaderHolder extends RecyclerView.ViewHolder {
    TextView mTvHeaderTitle;
    TextView mTvHeaderMore;

    public Lv4HeaderHolder(@NonNull View itemView) {
        super(itemView);
        mTvHeaderTitle = (TextView) itemView.findViewById(R.id.lv4_rv_tv_header_title);
        mTvHeaderMore = (TextView) itemView.findViewById(R.id.lv4_rv_tv_header_more);
    }

    public void setData() {
        mTvHeaderTitle.setText("推荐英雄");
        mTvHeaderMore.setText("查看更多 >");
    }
}

class Lv4BodyHolder extends RecyclerView.ViewHolder {
    TextView mTvBodyTitle;
    TextView mTvBodyContent;
    ImageView mIvBodyProfit;
    Button mBtnBodyFollow;

    public Lv4BodyHolder(View itemView) {
        super(itemView);
        mTvBodyTitle = (TextView) itemView.findViewById(R.id.lv4_rv_tv_body_title);
        mTvBodyContent = (TextView) itemView.findViewById(R.id.lv4_rv_tv_body_content);
        mIvBodyProfit = (ImageView) itemView.findViewById(R.id.lv4_rv_iv_body_profit);
        mBtnBodyFollow = (Button) itemView.findViewById(R.id.lv4_rv_btn_body_follow);
    }

    public void setData(ArrayList<Lv4Body> data, int position) {
        mTvBodyTitle.setText(data.get(position).getBodyTitle());
        mTvBodyContent.setText(data.get(position).getBodyContent());
        mBtnBodyFollow.setText("关注");
        mIvBodyProfit.setImageResource(data.get(position).getImageId());
    }
}
