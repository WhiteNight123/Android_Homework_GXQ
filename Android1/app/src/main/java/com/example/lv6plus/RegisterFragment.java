package com.example.lv6plus;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;

public class RegisterFragment extends DialogFragment {

    private TextInputLayout mTilAccount;
    private TextInputLayout mTilPassword;
    private TextInputLayout mTilPasswordAgain;
    private TextInputLayout mTilPhone;
    private MaterialButton mButtonRegister;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        return view;
    }

    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(getActivity(), "注册功能还没有实现...", Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void initView() {
        mTilAccount = (TextInputLayout) getActivity().findViewById(R.id.til_register_account);
        mTilPassword = (TextInputLayout) getActivity().findViewById(R.id.til_register_password);
        mTilPhone = (TextInputLayout) getActivity().findViewById(R.id.til_register_phone);
        mTilPasswordAgain = (TextInputLayout) getActivity().findViewById(R.id.til_register_password_again);
        mButtonRegister = (MaterialButton) getActivity().findViewById(R.id.btn_register1);
        initTilView();
    }

    private void initTilView() {
        // 开启错误提示
        mTilAccount.setErrorEnabled(true);
        mTilPassword.setErrorEnabled(true);
        mTilPasswordAgain.setErrorEnabled(true);
        mTilPhone.setErrorEnabled(true);
        // 开启计数
        mTilAccount.setCounterEnabled(true);
        mTilPhone.setCounterEnabled(true);
        // 设置输入最大长度
        mTilAccount.setCounterMaxLength(10);
        mTilPhone.setCounterMaxLength(11);
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
                } else if (mTilPassword.getEditText().getText().toString().matches("^[0-9]+$")) {
                    mTilPassword.setError("密码安全度低");
                } else {
                    mTilPassword.setError(null);
                }
            }
        });
        mTilPasswordAgain.getEditText().addTextChangedListener(new TextWatcher() {
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
                if (mTilPassword.getEditText().getText().toString().trim().length() != 0) {
                    if (mTilPassword.getEditText().getText().toString().equals(mTilPasswordAgain.getEditText().getText().toString())) {
                        mTilPasswordAgain.setError(null);
                    } else {
                        mTilPasswordAgain.setError("两次密码不一致!");
                    }
                } else {
                    mTilPasswordAgain.setError(null);
                }
            }
        });

        mTilPhone.getEditText().addTextChangedListener(new TextWatcher() {
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
                if (mTilPhone.getEditText().getText().toString().trim().length() == 11) {
                    mTilPhone.setError(null);
                } else {
                    mTilPhone.setError("手机号不正确");
                }
            }
        });
    }


}
