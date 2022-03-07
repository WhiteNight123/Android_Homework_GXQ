package com.example.mvp.page;

import com.example.mvp.bean.QuestionData;

import java.util.ArrayList;

/**
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/2/20
 */
public interface QuestionView {

    void onLoadResult(ArrayList<QuestionData> data);

    void onSetProgressBarVisibility(int visibility);

    void error();

}
