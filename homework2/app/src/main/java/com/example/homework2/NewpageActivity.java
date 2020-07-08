package com.example.homework2;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NewpageActivity extends AppCompatActivity {
    private String position ;
    private static final String TAG = "TAG";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);
        Log.i(TAG, "LinearLayoutActivity onCreate");
        initView();
    }

    private void initView() {
        Intent intent=getIntent();
        position = intent.getStringExtra("position");
        TextView a=findViewById(R.id.new_text);
        a.setText("这是第"+position+"个item");
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "FrameLayoutActivity onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "FrameLayoutActivity onResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "FrameLayoutActivity onRestart");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "FrameLayoutActivity onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "FrameLayoutActivity onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "FrameLayoutActivity onDestroy");
    }

}
