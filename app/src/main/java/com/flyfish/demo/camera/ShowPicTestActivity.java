package com.flyfish.demo.camera;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Created by Danfeng on 2018/2/2.
 */

public class ShowPicTestActivity extends AppCompatActivity {
    private ImageView mImageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpic);
        String picpath=getIntent().getStringExtra("picpath");
        mImageView= (ImageView) findViewById(R.id.pic_image);
        try {
            FileInputStream fis=new FileInputStream(picpath);
            Bitmap bitmap= BitmapFactory.decodeStream(fis);
            Log.e("图片","宽"+bitmap.getWidth()+"高"+bitmap.getHeight());
            mImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


    }
}
