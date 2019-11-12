package com.feb.httptask;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.feb.httptask.bean.User;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void click(View view) {
        HttpHelper.setHttpRequest("http:xxx", "", User.class, new IDataListener<User>() {
            @Override
            public void onBizRequestSuccess(User requestInfo) {

            }

            @Override
            public void onBizRequestFail() {

            }
        });
    }
}
