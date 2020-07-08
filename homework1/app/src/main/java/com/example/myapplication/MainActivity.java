package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn = findViewById(R.id.bt_open);
        final TextView tv = findViewById(R.id.textView);
        Switch stc = findViewById(R.id.switch1);
        final TextView stc_tv = findViewById(R.id.textView2);
        stc.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean ischecked) {
                if(ischecked) {
                    stc_tv.setText("The switch is open");
                    Log.i("111111", "onCreate: The switch is open");
                }
                else{
                    stc_tv.setText("The switch is close");
                    Log.i("111111", "onCreate: The switch is close");
                }
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv.setText("I love ZJU");
                Log.i("111111", "onCreate: I love ZJU");
            }
        });
        EditText text = findViewById(R.id.editTextPassword);
        text.setText("success");
        Log.i("111111", "onCreate: success");
    }
}

