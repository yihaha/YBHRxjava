package com.ybhrxjavademo.viewtest;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by y on 2016/4/15.
 */
public class TestView extends View {
    private int mWidth, mHeigth;
    private Paint mPaint = new Paint();
    private float percentage;

    public TestView(Context context) {
        this(context, null);
    }

    public TestView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeigth = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        canvas.translate(mWidth / 2, mHeigth / 2);
//        mPaint.setColor(0xFFCCFF00);
//        canvas.drawCircle(0, 0, (float) (mWidth / 2 * 0.8), mPaint);
//        mPaint.setColor(0xFF7CFC00);
//        canvas.drawCircle(0, 0, (float) (mWidth / 2 * 0.7), mPaint);
//        mPaint.setColor(0xFF808080);
//        for (int i = 0; i < 360; i++) {
//            canvas.drawLine(0, (float) (mWidth / 2 * 0.7), 0, (float) (mWidth / 2 * 0.8), mPaint);
//            canvas.rotate(10);
//        }

        mPaint.setStyle(Paint.Style.FILL);
        RectF rectF = new RectF(0, 0, mWidth, mHeigth);
        mPaint.setColor(0xFF808000);
        canvas.drawRoundRect(rectF, 10, 10, mPaint);
        mPaint.setColor(0xFFFF8C69);
        RectF rectF1 = new RectF(0, 0, mWidth * percentage / 100, mHeigth);
        canvas.drawRoundRect(rectF1, 10, 10, mPaint);



    }

    public void setView(float cent) {
        percentage = cent;
        invalidate();
    }

}
