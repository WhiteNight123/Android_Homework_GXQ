package com.example.mvp.page;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.mvp.R;
import com.example.mvp.bean.QuestionData;
import com.example.mvp.presenter.QuestionPresenter;
import com.example.mvp.presenter.QuestionPresenterImpl;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements QuestionView {

    private ArrayList<QuestionData> data = new ArrayList<>();
    private ProgressBar progressBar;
    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private QuestionPresenter presenter;
    private int page = 1;
    private LinearLayoutManager layoutManager;
    private SwipeRefreshLayout refreshLayout;
    private static int lastVisibleItem = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        refreshLayout = findViewById(R.id.swipeRefreshLayout);
        recyclerView = findViewById(R.id.recycleView);
        adapter = new RecycleViewAdapter(data);
        recyclerView.setAdapter(adapter);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        presenter = new QuestionPresenterImpl(this);

        presenter.load1(page);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                page = 1;
                presenter.load1(page);
            }
        });
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    if (!adapter.isFadeTips() && lastVisibleItem + 1 == adapter.getItemCount()) {
                        page++;
                        presenter.load1(page);
                    }
                    if (adapter.isFadeTips() && lastVisibleItem + 2 == adapter.getItemCount()) {
                        page++;
                        // 然后调用updateRecyclerview方法更新RecyclerView
                        presenter.load1(page);
                    }
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                lastVisibleItem = layoutManager.findLastVisibleItemPosition();
            }
        });
    }

    @Override
    protected void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLoadResult(ArrayList<QuestionData> idata) {
        if (page == 1) {
            data.clear();
        }
        presenter.setProgressBarVisibility(View.INVISIBLE);
        if (refreshLayout.isRefreshing()) {
            refreshLayout.setRefreshing(false);
        }

        data.addAll(idata);
        adapter.notifyDataSetChanged();

    }

    @Override
    public void onSetProgressBarVisibility(int visibility) {
        progressBar.setVisibility(visibility);
    }

    @Override
    public void error() {
        Toast.makeText(this, "出错误了", Toast.LENGTH_SHORT).show();
    }
}