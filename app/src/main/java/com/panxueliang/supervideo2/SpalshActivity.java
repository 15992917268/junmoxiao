package com.panxueliang.supervideo2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;

public class SpalshActivity extends Activity {

    private SharedPreferences mSharePreference;
    private static final int GO_HOME = 1;
    private static final int GO_GUIDE = 2;
    private static final int ENTER_DURATION = 2000;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case GO_GUIDE:
                    startGuideActivity();
                    break;
                case GO_HOME:
                    startHomeActivity();
                    break;
                default:
                    break;

            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSharePreference = getSharedPreferences("config", MODE_PRIVATE);
        init();
    }

    private void init() {
        boolean isFirstIn = mSharePreference.getBoolean("mIsFirstInt", true);
        if (isFirstIn) {
            mHandler.sendEmptyMessageDelayed(GO_GUIDE, ENTER_DURATION);
        } else {
            mHandler.sendEmptyMessageDelayed(GO_HOME, ENTER_DURATION);
        }
    }

    private void startHomeActivity(){
        Intent intent = new Intent(SpalshActivity.this,HomeActivity.class);
        startActivity(intent);
        finish();
    };

    private void startGuideActivity(){
        Intent intent = new Intent(SpalshActivity.this,GuideActivity.class);
        startActivity(intent);
        finish();
    }
}