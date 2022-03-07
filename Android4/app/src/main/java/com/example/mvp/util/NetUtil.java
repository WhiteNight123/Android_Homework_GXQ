package com.example.mvp.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

/**
 * 安卓第3次作业封装的网络请求排上用场了
 *
 * @author WhiteNight123 (Guo Xiaoqiang)
 * @email 1448375249@qq.com
 * @data 2022/1/15
 */
public class NetUtil {
    public static void sendHttpRequest(final String address, String requestMethod, HashMap<String, String> params, final NetCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);

                    connection.setDoInput(true);
                    if ("POST".equals(requestMethod)) {
                        connection.setDoOutput(true);
                        StringBuilder dataToWrite = new StringBuilder();
                        for (String key : params.keySet()) {
                            dataToWrite.append(key).append("=").append(params.get(key)).append("&");
                        }
                        OutputStream outputStream = connection.getOutputStream();
                        outputStream.write(dataToWrite.substring(0, dataToWrite.length() - 1).getBytes());
                    }
                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String oneLine;
                    while ((oneLine = reader.readLine()) != null) {
                        response.append(oneLine);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    //带cookie的请求
    public static void sendHttpRequest(final String address, String cookie, final NetCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("cookie", cookie);

                    connection.setDoInput(true);

                    InputStream in = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String oneLine;
                    while ((oneLine = reader.readLine()) != null) {
                        response.append(oneLine);
                    }
                    if (listener != null) {
                        listener.onFinish(response.toString());
                    }
                } catch (Exception e) {
                    if (listener != null) {
                        listener.onError(e);
                    }
                } finally {
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }
}
