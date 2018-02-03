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

public class ShowPicActivity extends AppCompatActivity {
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
            ExifInterface exif = new ExifInterface(picpath);
            int ori = exif.getAttributeInt(ExifInterface.TAG_ORIENTATION,
                    ExifInterface.ORIENTATION_UNDEFINED);
            int digree=0;
            switch (ori) {
                case ExifInterface.ORIENTATION_ROTATE_90:
                    digree = 90;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_180:
                    digree = 180;
                    break;
                case ExifInterface.ORIENTATION_ROTATE_270:
                    digree = 270;
                    break;
                default:
                    digree = 90;
                    break;
            }
            Matrix matrix=new Matrix();
            matrix.setRotate(digree);
            Log.e("角度",digree+"" );
            bitmap=Bitmap.createBitmap(bitmap,0,0,bitmap.getWidth(),bitmap.getHeight(),matrix,true);
            mImageView.setImageBitmap(bitmap);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
