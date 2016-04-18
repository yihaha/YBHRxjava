package com.ybhrxjavademo;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ybhrxjavademo.viewtest.CircleData;
import com.ybhrxjavademo.viewtest.CircleView;
import com.ybhrxjavademo.viewtest.TestView;

import java.util.ArrayList;

public class ShowViewActivity extends AppCompatActivity {

    private CircleView mCircleView;
    private TestView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_view);
        mCircleView = (CircleView) findViewById(R.id.ycir_view);
        mCircleView.setVisibility(View.GONE);
        ArrayList<CircleData> circleDatas = new ArrayList<>();
        for (int i = 1; i < 6; i++) {
            CircleData circleData = new CircleData(i + "", i * 10);
            circleDatas.add(circleData);
        }
        mCircleView.setStartAngle(10);
        mCircleView.setData(circleDatas);


        mTestView = (TestView) findViewById(R.id.testview);
        mTestView.setView(50);

//        ObjectAnimator rotation = ObjectAnimator.ofFloat(mTestView, "rotation", 0f, 360f);
        ObjectAnimator rotation = ObjectAnimator.ofFloat(mTestView, "scaleY", 0.5f, 3f,1f);
        rotation.setDuration(3000);
        //下面两行配合起来会不断执行动画
        rotation.setRepeatCount(ValueAnimator.INFINITE);
        rotation.setRepeatMode(ValueAnimator.REVERSE);
        rotation.start();
    }
}
