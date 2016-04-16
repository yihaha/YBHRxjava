package com.ybhrxjavademo.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;

/**
 * Created by y on 2016/4/15.
 */
public class CircleView extends View {
    // 颜色表
    private int[] mColors = {0xFFCCFF00, 0xFF6495ED, 0xFFE32636
            , 0xFF800000, 0xFF808000, 0xFFFF8C69
            , 0xFF808080, 0xFFE6B800, 0xFF7CFC00};
    private ArrayList<CircleData> mDataList;
    private int w;
    private int mWidth;
    private int mHeight;
    private Paint paint;
    private float mStartAngle;

    public CircleView(Context context) {
//        super(context);
        this(context,null);
    }

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (null == mDataList || mDataList.size() == 0) {
            return;
        }

        float currentStartAngle=mStartAngle;
        canvas.translate(mWidth / 2, mHeight / 2); //画布原点移动到屏幕中心
        //半径
        float r = (float) (Math.min(mWidth, mHeight) / 2 * 0.8);
        RectF rectF = new RectF(-r, -r, r, r);
        for (int i = 0; i < mDataList.size(); i++) {
            CircleData circleData = mDataList.get(i);
            paint.setColor(circleData.getColor());
            canvas.drawArc(rectF,currentStartAngle,circleData.getAngle(),true,paint);
            currentStartAngle+=circleData.getAngle();
        }

    }

    /**
     * 设置其实角度
     *
     * @param startAngle
     */
    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        invalidate();
    }

    public void setData(ArrayList<CircleData> dataList) {
        mDataList = dataList;
        initData(mDataList);
        invalidate(); //刷新
    }

    private void initData(ArrayList<CircleData> dataList) {
        if (null == dataList || dataList.size() == 0) {
            return;
        }

        float sumValue = 0;
        //计算值的和,然后在计算每个值占的百分比
        for (int i = 0; i < dataList.size(); i++) {
            CircleData circleData = dataList.get(i);
            sumValue += circleData.getValue();
            int i1 = i % mColors.length;
            circleData.setColor(mColors[i1]);
        }

        //设置百分比,角度
        for (int i = 0; i < dataList.size(); i++) {
            CircleData circleData = dataList.get(i);
            float percentage = circleData.getValue() / sumValue;
            float angle = percentage*360;
            circleData.setPercentage(percentage);
            circleData.setAngle(angle);
        }


    }

}
