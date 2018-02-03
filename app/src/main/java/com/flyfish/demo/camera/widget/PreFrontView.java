package com.flyfish.demo.camera.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.flyfish.demo.camera.R;

/**
 * Created by Danfeng on 2018/2/2.
 */

public class PreFrontView extends View {
    private int mWidth;
    private int mHeight;
    private float mPadding;
    private Paint mPaint;
    private float mLineLength = 50;
    private float mLineWidth;
    private int mLineColor;

    public PreFrontView(Context context) {
        this(context, null);
    }

    public PreFrontView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);

    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.PreFrontView);
        mPadding = ta.getDimension(R.styleable.PreFrontView_fontview_padding, 30);
        mLineLength = ta.getDimension(R.styleable.PreFrontView_fontview_linelength, 30);
        mLineWidth = ta.getDimension(R.styleable.PreFrontView_fontview_linewidth, 3);
        mLineColor = ta.getColor(R.styleable.PreFrontView_fontview_linecolor, context.getResources().getColor(R.color.blue));
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setColor(mLineColor);
        mPaint.setStrokeWidth(mLineWidth);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float width = mWidth - 2 * mPadding;
        float height = mHeight - 2 * mPadding;
        if (Math.sqrt(2) * width <= height) {
            //TODO 以width为标准
            float halfheightspace = (float) ((height - Math.sqrt(2) * width) / 2);
            Float[] topleft = new Float[]{mPadding, mPadding + halfheightspace};
            Float[] topright = new Float[]{mPadding + width, mPadding + halfheightspace};
            Float[] bottomleft = new Float[]{mPadding, mPadding + halfheightspace + height};
            Float[] bottomright = new Float[]{mPadding + width, mPadding + halfheightspace + height};
            //左上
            Path topLeftPath = new Path();
            topLeftPath.moveTo(topleft[0] + mLineLength, topleft[1]);
            topLeftPath.lineTo(topleft[0], topleft[1]);
            topLeftPath.lineTo(topleft[0], topleft[1] + mLineLength);
            canvas.drawPath(topLeftPath, mPaint);
            //右上
            Path topRightPath = new Path();
            topRightPath.moveTo(topright[0] - mLineLength, topright[1]);
            topRightPath.lineTo(topright[0], topright[1]);
            topRightPath.lineTo(topright[0], topright[1] + mLineLength);
            canvas.drawPath(topRightPath, mPaint);
            //左下
            Path bottomLeftPath = new Path();
            bottomLeftPath.moveTo(bottomleft[0] + mLineLength, bottomleft[1]);
            bottomLeftPath.lineTo(bottomleft[0], bottomleft[1]);
            bottomLeftPath.lineTo(bottomleft[0], bottomleft[1] - mLineLength);
            canvas.drawPath(bottomLeftPath, mPaint);
            //右下
            Path bottomRightPath = new Path();
            bottomRightPath.moveTo(bottomright[0] - mLineLength, bottomright[1]);
            bottomRightPath.lineTo(bottomright[0], bottomright[1]);
            bottomRightPath.lineTo(bottomright[0], bottomright[1] - mLineLength);
            canvas.drawPath(bottomRightPath, mPaint);

        } else if (Math.sqrt(2) * width > height) {
            //TODO 以height为标准 纸张标准的w： w=h/Math.sqrt(2)
            float halfwidthspace = (float) ((width - height / Math.sqrt(2)) / 2.0);
            Float[] topleft = new Float[]{mPadding + halfwidthspace, mPadding};
            Float[] topright = new Float[]{mPadding + halfwidthspace + width, mPadding};
            Float[] bottomleft = new Float[]{mPadding + halfwidthspace, mPadding + height};
            Float[] bottomright = new Float[]{mPadding + halfwidthspace + width, mPadding + height};
            //左上
            Path topLeftPath = new Path();
            topLeftPath.moveTo(topleft[0] + mLineLength, topleft[1]);
            topLeftPath.lineTo(topleft[0], topleft[1]);
            topLeftPath.lineTo(topleft[0], topleft[1] + mLineLength);
            canvas.drawPath(topLeftPath, mPaint);
            //右上
            Path topRightPath = new Path();
            topRightPath.moveTo(topright[0] - mLineLength, topright[1]);
            topRightPath.lineTo(topright[0], topright[1]);
            topRightPath.lineTo(topright[0], topright[1] + mLineLength);
            canvas.drawPath(topRightPath, mPaint);
            //左下
            Path bottomLeftPath = new Path();
            bottomLeftPath.moveTo(bottomleft[0] + mLineLength, bottomleft[1]);
            bottomLeftPath.lineTo(bottomleft[0], bottomleft[1]);
            bottomLeftPath.lineTo(bottomleft[0], bottomleft[1] - mLineLength);
            canvas.drawPath(bottomLeftPath, mPaint);
            //右下
            Path bottomRightPath = new Path();

            bottomRightPath.moveTo(bottomright[0] - mLineLength, bottomright[1]);
            bottomRightPath.lineTo(bottomright[0], bottomright[1]);
            bottomRightPath.lineTo(bottomright[0], bottomright[1] - mLineLength);
            canvas.drawPath(bottomRightPath, mPaint);
        }
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mWidth = getDefaultSize(getSuggestedMinimumWidth(), widthMeasureSpec);
        mHeight = getDefaultSize(getSuggestedMinimumHeight(), heightMeasureSpec);
        setMeasuredDimension(mWidth, mHeight);
    }
}
