package com.ybhrxjavademo;

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

    }
}
