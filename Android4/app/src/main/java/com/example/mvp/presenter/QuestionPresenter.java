package com.example.mvp.presenter;

/**
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/2/20
 */
public interface QuestionPresenter {
    void load1(int page);

    void setProgressBarVisibility(int visiblity);

    void onDestroy();
}
