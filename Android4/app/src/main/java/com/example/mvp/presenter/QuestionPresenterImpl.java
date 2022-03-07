package com.example.mvp.presenter;

import android.os.Handler;
import android.os.Message;
import android.view.View;

import androidx.annotation.NonNull;

import com.example.mvp.bean.QuestionData;
import com.example.mvp.model.QuestionModelImpl;
import com.example.mvp.page.QuestionView;

import java.util.ArrayList;

/**
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/2/20
 */
public class QuestionPresenterImpl implements QuestionPresenter, QuestionModelImpl.DataLoadListener {
    private QuestionView questionView;
    private QuestionModelImpl loginModel;
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            switch (msg.what) {
                case 1:
                    questionView.onLoadResult((ArrayList<QuestionData>) msg.obj);
                    break;
                case 2:
                    questionView.error();
                    break;
                default:
                    break;
            }

            return false;
        }
    });

    public QuestionPresenterImpl(QuestionView questionView) {
        this.questionView = questionView;
        this.loginModel = new QuestionModelImpl();
        loginModel.setListener(this);
    }

    @Override
    public void load1(int page) {
        if (questionView != null) {
            questionView.onSetProgressBarVisibility(View.VISIBLE);
        }
        loginModel.load2(page);
    }

    @Override
    public void setProgressBarVisibility(int visibility) {
        questionView.onSetProgressBarVisibility(visibility);
    }

    @Override
    public void onDestroy() {
        questionView = null;
    }


    @Override
    public void failure(Exception e) {
        e.printStackTrace();
        Message message = new Message();
        message.what = 2;
        handler.sendMessage(message);
    }

    @Override
    public void success(ArrayList<QuestionData> dataArrayList) {
        if (questionView != null) {
            Message message = new Message();
            message.what = 1;
            message.obj = dataArrayList;
            handler.sendMessage(message);

        }
    }
}
