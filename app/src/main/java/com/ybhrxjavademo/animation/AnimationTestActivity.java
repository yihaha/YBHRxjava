package com.ybhrxjavademo.animation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.ybhrxjavademo.R;

public class AnimationTestActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animation_test);
        findViewById(R.id.tween_ani).setOnClickListener(this);
        findViewById(R.id.fra_ani).setOnClickListener(this);
        findViewById(R.id.property_ani).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        String tag = "";
        switch (v.getId()) {
            case R.id.tween_ani: //补间动画
                intent = new Intent(this, TweenAniActivity.class);
                break;
            case R.id.fra_ani: //帧动画
                intent = new Intent(this, FramAnimationActivity.class);
                break;

            case R.id.property_ani: //属性动画
                intent = new Intent(this, ProterAniActivity.class);
                break;
        }
        startActivity(intent);

    }
}
