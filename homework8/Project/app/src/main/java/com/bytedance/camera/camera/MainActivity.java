package com.bytedance.camera.camera;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.bytedance.camera.camera.utils.Utils;

public class MainActivity extends AppCompatActivity {
    private final static int REQUEST_CODE = 100;
    private String[] permissions = new String[] {
            Manifest.permission.CAMERA,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.RECORD_AUDIO

    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        findViewById(R.id.btn_custom).setOnClickListener(v -> {
            if(!Utils.isPermissionsReady(MainActivity.this,permissions)){
                Utils.reuqestPermissions(this,permissions,REQUEST_CODE);
            }else {
                startActivity(new Intent(MainActivity.this, CustomCameraActivity.class));
            }

        });
    }

}
