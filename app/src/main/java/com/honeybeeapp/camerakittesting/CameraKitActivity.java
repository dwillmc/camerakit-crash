package com.honeybeeapp.camerakittesting;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.wonderkiln.camerakit.CameraView;

public class CameraKitActivity extends AppCompatActivity {

    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_kit);

        mCameraView = findViewById(R.id.camera);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mCameraView.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mCameraView.stop();
    }
}
