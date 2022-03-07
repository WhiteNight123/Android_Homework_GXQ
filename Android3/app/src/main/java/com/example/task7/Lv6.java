package com.example.task7;

import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Lv6 extends AppCompatActivity implements View.OnClickListener {

    private Handler mHandler;
    private Button mButtonRegister;
    private Button mButtonLogin;
    private Button mButtonClearCookie;
    private EditText mEtAccount;
    private EditText mEtPassword;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    String errorMsg;
    int errorCode;

    private class Lv6Handler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mHandler = new Lv6Handler();
            String responseData = msg.obj.toString();
            jsonDecodeTest(responseData);
            switch (msg.what) {
                case 1:
                    if (errorCode == -1) {
                        Toast.makeText(Lv6.this, errorMsg, Toast.LENGTH_SHORT).show();
                        editor.remove("cookie").commit(); //登录不成功,清除cookies
                    } else {
                        Toast.makeText(Lv6.this, "登录成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case 2:
                    if (errorCode == -1) {
                        Toast.makeText(Lv6.this, "cookie信息过期了", Toast.LENGTH_SHORT).show();
                        editor.remove("cookie").commit();
                    } else {
                        Toast.makeText(Lv6.this, "免登录成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
                default:
                    break;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv6_activity);
        initView();
        mButtonLogin.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
        mButtonClearCookie.setOnClickListener(this);

        if (!pref.getString("cookie", "").equals("")) {
            //System.out.println("免登录 " + pref.getString("cookie", ""));
            autoLogin("https://wanandroid.com//user/lg/userinfo/json");
        }
    }

    public void initView() {
        mButtonLogin = (Button) findViewById(R.id.lv6_btn_login);
        mButtonRegister = (Button) findViewById(R.id.lv6_btn_register);
        mEtAccount = (EditText) findViewById(R.id.lv6_et_login_account);
        mEtPassword = (EditText) findViewById(R.id.lv6_et_login_password);
        mButtonClearCookie = (Button) findViewById(R.id.lv6_tv_clear_cookie);
        pref = PreferenceManager.getDefaultSharedPreferences(this);
        editor = pref.edit();
        mHandler = new Lv6Handler();
    }

    public void autoLogin(String mUrl) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestProperty("cookie", pref.getString("cookie", ""));
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    InputStream in = connection.getInputStream();
                    String responseData = StreamToString(in);
                    Message message = new Message();
                    message.what = 2;
                    message.obj = responseData;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }


    private void sendPostNetRequest(String mUrl, HashMap<String, String> params) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    URL url = new URL(mUrl);
                    HttpURLConnection.setFollowRedirects(true);
                    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    connection.setDoOutput(true);
                    connection.setDoInput(true);
                    connection.connect();
                    StringBuilder dataToWrite = new StringBuilder();
                    for (String key : params.keySet()) {
                        dataToWrite.append(key).append("=").append(params.get(key)).append("&");
                    }
                    OutputStream outputStream = connection.getOutputStream();
                    outputStream.write(dataToWrite.substring(0, dataToWrite.length() - 1).getBytes());
                    InputStream in = connection.getInputStream();
                    String responseData = StreamToString(in);

                    Map<String, List<String>> cookies = connection.getHeaderFields();
                    List<String> setCookies = cookies.get("Set-Cookie");
                    String cookie = "";
                    for (String str : setCookies) {
                        cookie = str + ";";
                    }
                    editor.putString("cookie", cookie).commit();
//                    for (String str : setCookies) {
//                        System.out.println("手动登录" + str + "\n");
//                    }

                    Message message = new Message();
                    message.what = 1;
                    message.obj = responseData;
                    mHandler.sendMessage(message);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private String StreamToString(InputStream in) {
        StringBuilder sb = new StringBuilder();
        String oneLine;
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try {
            while ((oneLine = reader.readLine()) != null) {
                sb.append(oneLine).append('\n');
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    private void jsonDecodeTest(String jsonData) {
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            errorCode = jsonObject.getInt("errorCode");
            errorMsg = jsonObject.getString("errorMsg");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearCookie() {
        if (!pref.getString("cookie", "").equals("")) {
            editor.remove("cookie").commit();
            Toast.makeText(Lv6.this, "清除cookie成功", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(Lv6.this, "没有cookie信息", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.lv6_btn_login:
                String account = mEtAccount.getText().toString();
                String password = mEtPassword.getText().toString();
                HashMap<String, String> map = new HashMap<>();
                map.put("username", account);
                map.put("password", password);
                sendPostNetRequest("https://www.wanandroid.com/user/login", map);
//                Lv4HttpUtil.sendHttpRequest("https://www.wanandroid.com/user/login", "POST", map, new Lv4HttpCallbackListener() {
//                    @Override
//                    public void onFinish(String response) {
//                        Message msg = new Message();
//                        msg.what = 1;
//                        msg.obj = response;
//                        handler.sendMessage(msg);
//                    }
//
//                    @Override
//                    public void onError(Exception e) {
//                    }
//                });
                break;
            case R.id.lv6_btn_register:
                new Lv6Dialog(Lv6.this).show();
                break;
            case R.id.lv6_tv_clear_cookie:
                clearCookie();
            default:
                break;
        }
    }
}