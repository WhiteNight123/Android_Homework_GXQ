package com.example.lv6plus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.textfield.TextInputLayout;

public class LoginFragment extends Fragment implements View.OnClickListener {
    private MaterialButton mButtonLogin;
    private TextInputLayout mTilAccount;
    private TextInputLayout mTilPassword;
    private Button mButtonRetrieve;
    private Button mButtonRegister;
    private ShapeableImageView mSivQqLogin;
    private ShapeableImageView mSivWechatLogin;
    private ShapeableImageView mSivGithubLogin;
    private CheckBox mRememberPass;
    private CheckBox mAutoLogin;
    private ProgressBar mProgressBar;
    private Handler mHandler;
    private SharedPreferences pref;//通过pref读取SharedPreferences的数据
    private SharedPreferences.Editor editor;//editor将数据写入SharedPreferences
    private int mProgress = 15;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        rememberPass();
        autoLogin();
        mButtonRetrieve.setOnClickListener(this);
        mButtonRegister.setOnClickListener(this);
        mButtonLogin.setOnClickListener(this);
        mSivQqLogin.setOnClickListener(this);
        mSivWechatLogin.setOnClickListener(this);
        mSivGithubLogin.setOnClickListener(this);
        mAutoLogin.setOnClickListener(this);
    }

    private void initView() {
        mButtonLogin = (MaterialButton) getActivity().findViewById(R.id.btn_login);
        mTilAccount = (TextInputLayout) getActivity().findViewById(R.id.til_login_account);
        mTilPassword = (TextInputLayout) getActivity().findViewById(R.id.til_login_password);
        mButtonRetrieve = (Button) getActivity().findViewById(R.id.btn_retrieve);
        mButtonRegister = (Button) getActivity().findViewById(R.id.btn_register);
        mSivQqLogin = (ShapeableImageView) getActivity().findViewById(R.id.siv_qq_login);
        mSivWechatLogin = (ShapeableImageView) getActivity().findViewById(R.id.siv_wechat_login);
        mSivGithubLogin = (ShapeableImageView) getActivity().findViewById(R.id.siv_github_login);
        mRememberPass = (CheckBox) getActivity().findViewById(R.id.cb_remember_pass);
        mAutoLogin = (CheckBox) getActivity().findViewById(R.id.cb_auto_login);
        mProgressBar = getActivity().findViewById(R.id.pb_login);
        pref = PreferenceManager.getDefaultSharedPreferences(getActivity());
        initTextInputLayout();
    }

    private void initTextInputLayout() {
        // 开启错误提示
        mTilAccount.setErrorEnabled(true);
        mTilPassword.setErrorEnabled(true);
        // 开启计数
        mTilAccount.setCounterEnabled(true);
        // 设置输入最大长度
        mTilAccount.setCounterMaxLength(10);
        // 设置浮动标签字体样式
        mTilAccount.setHintTextAppearance(R.style.hintAppearance);
        mTilPassword.setHintTextAppearance(R.style.hintAppearance);

        mTilAccount.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本变化前调用
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本发生变化时调用
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文本发生变化后调用
                if (mTilAccount.getEditText().getText().toString().trim().length() > 10) {
                    mTilAccount.setError("用户名长度超出限制!");
                } else if (mTilAccount.getEditText().getText().toString().trim().length() == 0) {
                    mTilAccount.setError("账号不能为空!");
                } else {
                    mTilAccount.setError(null);
                }
            }
        });
        mTilPassword.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // 文本变化前调用
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // 文本发生变化时调用
            }

            @Override
            public void afterTextChanged(Editable s) {
                // 文本发生变化后调用
                if (mTilPassword.getEditText().getText().toString().trim().length() > 16) {
                    mTilPassword.setError("密码长度超出16位!");
                } else if (mTilPassword.getEditText().getText().toString().trim().length() < 6) {
                    mTilPassword.setError("密码长度不足6位!");
                } else {
                    mTilPassword.setError(null);
                }
            }
        });
    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.text_layout, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    private void loginProgressBar() {

        if (mProgressBar.getVisibility() == View.INVISIBLE) {
            mProgressBar.setVisibility(View.VISIBLE);
        }
        mHandler = new Handler() {
            @Override
            public void handleMessage(@NonNull Message msg) {
                if (msg.what == 0x111) {
                    mProgressBar.setProgress(mProgress);
                } else {
                    Toast.makeText(getActivity(), "登录成功", Toast.LENGTH_SHORT).show();
                    mProgressBar.setVisibility(View.INVISIBLE);
                }
            }
        };

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    mProgress = doWork();
                    Log.i("TAG", "handleMessage: " + mProgress);
                    Message m = new Message();
                    if (mProgress < 100) {
                        m.what = 0x111;
                        mHandler.sendMessage(m);
                    } else {
                        m.what = 0x110;
                        mHandler.sendMessage(m);
//                        Intent intent = new Intent(MainActivity.this, Main2Activity.class);
//                        startActivity(intent);
                        replaceFragment(new ContentFragment());
                        break;
                    }
                }
            }

            private int doWork() {
                mProgress += Math.random() * 20;
                try {
                    Thread.sleep(200);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return mProgress;
            }
        }).start();
    }

    private void autoLogin(){
        boolean isAutoLogin=pref.getBoolean("auto_login",false);
        if(isAutoLogin){
            mAutoLogin.setChecked(true);
            login();
        }
    }
    private void rememberPass() {
        boolean isRemember = pref.getBoolean("remember_password", false);//读取上次登陆时存入"remember_password"的信息，没有读取到则默认为false
        if (isRemember)//如果读取为true，则将account和password，checkbox的信息写入文本框
        {
            String account = pref.getString("account", "");
            String password = pref.getString("password", "");
            mTilAccount.getEditText().setText(account);
            mTilPassword.getEditText().setText(password);
            mRememberPass.setChecked(true);
        }
    }

    private void login() {
        String account = mTilAccount.getEditText().getText().toString();
        String password = mTilPassword.getEditText().getText().toString();
        //验证用户名和密码
        if (account.equals("123456") && password.equals("123456")) {
            editor = pref.edit();
            if (mRememberPass.isChecked()) {//如果勾选了checkbox框，则将account，password，checkbox信息写入
                editor.putBoolean("remember_password", true);
                editor.putString("account", account);
                editor.putString("password", password);
            } else {
                editor.clear();//若没有，清除SharedPreferences存储的信息
            }
            if (mAutoLogin.isChecked()) {
                editor.putBoolean("auto_login", true);
            }
            editor.apply();
            loginProgressBar();
       } else
            Toast.makeText(getActivity(), "账号或密码错误", Toast.LENGTH_SHORT).show();
    }

    private void retrieve() {
        Uri uri = Uri.parse("https://accounts.qq.com/find/password?from=11");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void qqLogin() {
        Uri uri = Uri.parse("https://cpo.qq.com/");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }

    private void register() {
        replaceFragment(new RegisterFragment());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                login();
                break;
            case R.id.btn_retrieve:
                retrieve();
                break;
            case R.id.btn_register:
                register();
                break;
            case R.id.siv_qq_login:
                qqLogin();
                break;
            case R.id.cb_auto_login:
                mRememberPass.setChecked(mAutoLogin.isChecked()); //点击自动登录同时会点击记住密码
                break;
            case R.id.siv_wechat_login:
                Toast.makeText(getActivity(), "微信登录功能还在开发中...", Toast.LENGTH_SHORT).show();
            case R.id.siv_github_login:
                Toast.makeText(getActivity(), "Github登录功能还在开发中...", Toast.LENGTH_SHORT).show();
        }
    }
}
