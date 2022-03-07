package com.example.mvp.util;

/**
 * 网络请求接口回调
 *
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/1/15
 */
public interface NetCallbackListener {
    void onFinish(String response);

    void onError(Exception e);
}
