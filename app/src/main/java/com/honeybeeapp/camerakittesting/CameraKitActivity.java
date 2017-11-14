package com.honeybeeapp.camerakittesting;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.wonderkiln.camerakit.CameraKitError;
import com.wonderkiln.camerakit.CameraKitEvent;
import com.wonderkiln.camerakit.CameraKitEventListenerAdapter;
import com.wonderkiln.camerakit.CameraKitImage;
import com.wonderkiln.camerakit.CameraKitVideo;
import com.wonderkiln.camerakit.CameraView;

public class CameraKitActivity extends AppCompatActivity {

    private static final String LOG_TAG = CameraKitActivity.class.getName();
    private CameraView mCameraView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera_kit);

        mCameraView = findViewById(R.id.camera);

        mCameraView.addCameraKitListener(new CameraKitEventListenerAdapter() {
            @Override
            public void onEvent(CameraKitEvent event) {
                super.onEvent(event);
                Log.d(LOG_TAG, "onEvent callback " + getDebugString(event));
            }

            @Override
            public void onError(CameraKitError error) {
                super.onError(error);
                Log.d(LOG_TAG, "onError callback " + getDebugString(error));
            }

            @Override
            public void onImage(CameraKitImage image) {
                super.onImage(image);

                String suffix = getDebugString(image);
                if (image.getJpeg() == null) {
                    suffix += " null Jpeg data";
                } else {
                    suffix += " non-null Jpeg data";
                }

                final String message = "onImage callback " + suffix;
                Toast.makeText(CameraKitActivity.this, message, Toast.LENGTH_LONG).show();
                Log.d(LOG_TAG, message );
            }

            @Override
            public void onVideo(CameraKitVideo video) {
                super.onVideo(video);
                Log.d(LOG_TAG, "onVideo callback " + getDebugString(video));
            }
        });

        final View mCaptureButton = findViewById(R.id.button2);
        mCaptureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCameraView.captureImage();
            }
        });

    }

    @NonNull
    private String getDebugString(CameraKitEvent event) {
        return event.getType() + event.getMessage();
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
