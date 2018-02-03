package com.flyfish.demo.camera;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.flyfish.demo.camera.widget.CameraPreview;
import com.flyfish.demo.camera.widget.PreFrontView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Danfeng on 2018/2/1.
 */

public class TakePhotoTestActivity extends AppCompatActivity {
    private CameraPreview mSurfaceView;
    private PreFrontView mPreFrontView;
    private String mPicPath = Environment.getExternalStorageDirectory() + File.separator + "test";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_democamera);
        init();
    }

    private void init() {
        mSurfaceView = (CameraPreview) findViewById(R.id.camera_surfaceview);
        mPreFrontView = findViewById(R.id.frame_imageview);
        mPreFrontView.setClickable(false);
        File file = new File(mPicPath);
        if (!file.exists()) {
            file.mkdirs();
        }
    }

    public void takePicture(View view) {
        mSurfaceView.setOnTakePhotoListener(new CameraPreview.TakePhotoListener() {
            @Override
            public void takephoto(byte[] data, Camera camera) {
                Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);
                //这里是因为相机是以横屏为标准的，因此相机预览的PreviewSize的width相当于我们竖屏看到的height
                float scalex= mSurfaceView.getWidth()/bitmap.getHeight();
                float scaley=mSurfaceView.getHeight()/bitmap.getWidth();
                Matrix matrix = new Matrix();
                matrix.postRotate(90);
                matrix.postScale(scalex,scaley);
                bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
                String filename = System.currentTimeMillis() + ".jpg";
                File tempFile = new File(mPicPath, filename);
                try {
                    FileOutputStream fos = new FileOutputStream(tempFile);
                    bitmap.compress(Bitmap.CompressFormat.JPEG,100,fos);
                    fos.flush();
                    fos.close();
                    Uri localUri = Uri.fromFile(tempFile);
                    Intent localIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, localUri);
                    sendBroadcast(localIntent);
                    Intent intent = new Intent(TakePhotoTestActivity.this, ShowPicTestActivity.class);
                    intent.putExtra("picpath", tempFile.getAbsolutePath());
                    startActivity(intent);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        mSurfaceView.startPreview();

    }

    @Override
    protected void onPause() {
        super.onPause();
        mSurfaceView.releaseCamera();
    }
}
