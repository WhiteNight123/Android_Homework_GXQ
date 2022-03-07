package com.example.mvp.page;

import android.os.Handler;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.mvp.R;
import com.example.mvp.bean.QuestionData;

import java.util.ArrayList;

/**
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/1/17
 */
public class RecycleViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private ArrayList<QuestionData> mData;
    private HomeRecycleViewListener mListener;
    private int normalType = 0;     // 第一种ViewType，正常的item
    private int footType = 1;       // 第二种ViewType，底部的提示View
    public static boolean hasMore = true;   // 变量，是否有更多数据
    private boolean fadeTips = false; // 变量，是否隐藏了底部的提示

    public RecycleViewAdapter(ArrayList<QuestionData> data) {
        this.mData = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==normalType){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
            InnerHolder holder = new InnerHolder(view);
            return holder;
        }
        else{
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.footview, parent, false);
           FootHolder holder = new FootHolder(view);
            return holder;
        }
//        holder.itemView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                int position = holder.getAdapterPosition();
//                String url = mData.get(position).getUrl();
//                mListener.onHomeRecycleViewClick(view, url, position);
//            }
//        });

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if(holder instanceof InnerHolder) {
            ((InnerHolder) holder).tvAuthor.setText(mData.get(position).getAuthor());
            ((InnerHolder) holder).tvTime.setText(mData.get(position).getTime());
            ((InnerHolder) holder).tvTitle.setText(Html.fromHtml(mData.get(position).getTitle(), Html.FROM_HTML_MODE_COMPACT));
            ((InnerHolder) holder).tvContent.setText(mData.get(position).getContent());
            ((InnerHolder) holder).tvChapterName.setText(mData.get(position).getChapterName());
        }else{
            if(hasMore){
                fadeTips=false;
                if(mData.size()>0){
                    ((FootHolder) holder).tips.setVisibility(View.VISIBLE);
                    ((FootHolder) holder).tips.setText("正在加载更多...");

                }
            }else{
                if (mData.size()>0){
                    ((FootHolder)holder).tips.setText("暂无更多数据");
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            // 隐藏提示条
                            ((FootHolder) holder).tips.setVisibility(View.GONE);
                            // 将fadeTips设置true
                            fadeTips = true;
                            // hasMore设为true是为了让再次拉到底时，会先显示正在加载更多
                            hasMore = true;
                        }
                    }, 1000);
                }
            }
        }
    }


    @Override
    public int getItemCount() {
        return mData.size()+1;
    }

     class InnerHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor;
        TextView tvTime;
        TextView tvTitle;
        TextView tvContent;
        TextView tvChapterName;

        public InnerHolder(@NonNull View itemView) {
            super(itemView);
            tvAuthor = itemView.findViewById(R.id.home_tv_rv_author);
            tvTime = itemView.findViewById(R.id.home_tv_rv_time);
            tvTitle = itemView.findViewById(R.id.home_tv_rv_title);
            tvContent = itemView.findViewById(R.id.home_tv_rv_content);
            tvChapterName = itemView.findViewById(R.id.home_tv_rv_chaptername);
        }
    }
    // 底部footView的ViewHolder，用以缓存findView操作
    class FootHolder extends RecyclerView.ViewHolder {
        private TextView tips;

        FootHolder(View itemView) {
            super(itemView);
            tips = itemView.findViewById(R.id.tips);
        }
    }
    // 自定义方法，获取数据的最后一个位置，不计上footView
    public int getRealLastPosition() {
        return mData.size();
    }
    // 根据条目位置返回ViewType，以供onCreateViewHolder方法内获取不同的Holder
    @Override
    public int getItemViewType(int position) {
        if (position == getItemCount() - 1) {
            return footType;
        } else {
            return normalType;
        }
    }

    // 暴露接口，改变fadeTips的方法
    public boolean isFadeTips() {
        return fadeTips;
    }

    //增加点击监听
    public void setOnHomeRecycleViewListener(HomeRecycleViewListener listener) {
        this.mListener = listener;
    }

    //点击监听回调接口
    public interface HomeRecycleViewListener {
        void onHomeRecycleViewClick(View view, Object data, int position);
    }
}
