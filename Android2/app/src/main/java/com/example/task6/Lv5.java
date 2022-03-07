package com.example.task6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;

public class Lv5 extends AppCompatActivity {
    int i = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.lv5_activity);
        LottieAnimationView animationView = (LottieAnimationView) findViewById(R.id.lv5_lav_button);
        animationView.setProgress(0.4f);
        mode2();
        mode1();
        animationView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                i++;
                if (i % 2 == 1) {
                    animationView.playAnimation();
                }
                if (i % 2 == 0) {
                    animationView.setProgress(0.4f);
                }
            }
        });

    }

    private void mode2() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("同意<<用户协议>>和<<隐私权政策>>");
        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#2CDFFF"));
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(Lv5.this, "用户协议", Toast.LENGTH_SHORT).show();
            }
        };
        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setColor(Color.parseColor("#2CDFFF"));
                ds.setUnderlineText(false);
            }

            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(Lv5.this, "隐私权政策", Toast.LENGTH_SHORT).show();
            }
        };
        //ForegroundColorSpan colorSpan1 = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        //ForegroundColorSpan colorSpan2 = new ForegroundColorSpan(Color.parseColor("#009ad6"));
        spannableString.setSpan(clickableSpan1, 2, 10, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        spannableString.setSpan(clickableSpan2, 11, 20, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
        ((TextView) findViewById(R.id.lv5_tv_protocol)).setText(spannableString);
        ((TextView) findViewById(R.id.lv5_tv_protocol)).setHighlightColor(Color.TRANSPARENT);
        ((TextView) findViewById(R.id.lv5_tv_protocol)).setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void mode1() {
        SpannableStringBuilder spannableString = new SpannableStringBuilder();
        spannableString.append("还没有账号吗? 试试游客模式吧");
        ClickableSpan clickableSpan = new ClickableSpan() {
            @Override
            public void updateDrawState(@NonNull TextPaint ds) {
                super.updateDrawState(ds);
                ds.setFakeBoldText(true);
                ds.setColor(Color.parseColor("#15315B"));
                ds.setUnderlineText(false);//去掉下划线
            }

            @Override
            public void onClick(@NonNull View widget) {
                Toast.makeText(Lv5.this, "游客模式", Toast.LENGTH_SHORT).show();
            }
        };
        StyleSpan styleSpan = new StyleSpan(Typeface.BOLD);//粗体
        spannableString.setSpan(clickableSpan, 8, 15, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
        ((TextView) findViewById(R.id.lv5_tv_login_other)).setText(spannableString);
        ((TextView) findViewById(R.id.lv5_tv_login_other)).setMovementMethod(LinkMovementMethod.getInstance());
        ((TextView) findViewById(R.id.lv5_tv_login_other)).setHighlightColor(Color.TRANSPARENT);
    }
}