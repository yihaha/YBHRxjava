package com.ybhrxjavademo.animation.propertyAnimation;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by y on 2016/4/18.
 */
public class YProterView extends View {
    private float RADUS = 50F; //半径
    private Point currPoint;
    private String color;
    private Paint paint;

    public YProterView(Context context) {
        this(context, null);
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    public YProterView(Context context, AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setColor(Color.RED);
        if (null==currPoint){
            currPoint=new Point(RADUS,RADUS);
            drawCircleView(canvas);
            mStartAnimation();
        }else {
            drawCircleView(canvas);
        }

    }

    private void drawCircleView(Canvas canvas) {
        float x = currPoint.getX();
        float y = currPoint.getY();
        canvas.drawCircle(x, y, RADUS, paint);
    }

    private void mStartAnimation() {
        Point startPoint = new Point(RADUS, RADUS);
        Point endPoint = new Point(getWidth() - RADUS, getHeight() - RADUS);
        ValueAnimator valueAnimator = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {


            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currPoint = (Point) animation.getAnimatedValue();
                invalidate();
            }
        });


        ObjectAnimator colorAni = ObjectAnimator.ofObject(this, "color", new ColorEvaluator(), "#0000FF", "#FF0000");

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(3000);
        animatorSet.play(valueAnimator).with(colorAni);
        animatorSet.start();
//        valueAnimator.setDuration(5000);
//        valueAnimator.start();
    }
}
