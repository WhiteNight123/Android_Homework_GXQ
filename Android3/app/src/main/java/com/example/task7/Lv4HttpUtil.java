package com.example.task7;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

public class Lv4HttpUtil {
    public static void sendHttpRequest(final String address, String requestMethod,
                                       HashMap<String, String> params, final Lv4HttpCallbackListener listener) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                try {
                    URL url = new URL(address);
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod(requestMethod);
                    connection.setReadTimeout(8000);
                    connection.setConnectTimeout(8000);
                    connection.setDoInput(true);
                    connection.setDoOutput(true);
                    if ("POST".equals(requestMethod)) {
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
}
