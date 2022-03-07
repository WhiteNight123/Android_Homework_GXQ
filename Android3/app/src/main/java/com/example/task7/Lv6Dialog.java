package com.example.task7;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.HashMap;

public class Lv6Dialog extends Dialog {
    private Handler mHandler;
    private Button mButtonRegister;
    private EditText mEtAccount;
    private EditText mEtPassword;
    private EditText mEtRePassword;
    String errorMsg;
    int errorCode;

    private class Lv6DialogHandler extends Handler {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            mHandler = new Lv6DialogHandler();
            String responseData = msg.obj.toString();
            jsonDecodeTest(responseData);
            if (errorCode == -1) {
                Toast.makeText(getOwnerActivity(), errorMsg, Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getOwnerActivity(), "注册成功", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public Lv6Dialog(@NonNull Context context) {
        super(context);
        if (context instanceof Activity) {
            setOwnerActivity((Activity) context); //防止空指针错误
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lv6_dialog);
        setCanceledOnTouchOutside(false);
        initView();
        mHandler = new Lv6DialogHandler();
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

    }

    public void initView() {
        mButtonRegister = (Button) findViewById(R.id.lv6_btn_register);
        mEtAccount = (EditText) findViewById(R.id.lv6_et_register_account);
        mEtPassword = (EditText) findViewById(R.id.lv6_et_register_password);
        mEtRePassword = (EditText) findViewById(R.id.lv6_et_register_repassword);
    }

    private void register() {
        String account = mEtAccount.getText().toString();
        String password = mEtPassword.getText().toString();
        String rePassword = mEtRePassword.getText().toString();
        HashMap<String, String> map = new HashMap<>();
        map.put("username", account);
        map.put("password", password);
        map.put("repassword", rePassword);
        Lv4HttpUtil.sendHttpRequest("https://www.wanandroid.com/user/register", "POST", map, new Lv4HttpCallbackListener() {
            @Override
            public void onFinish(String response) {
                Message message = new Message();
                message.obj = response;
                mHandler.sendMessage(message);
            }

            @Override
            public void onError(Exception e) {

            }
        });
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
}
