package com.ybhrxjavademo.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import com.ybhrxjavademo.R;

public class TweenAniActivity extends AppCompatActivity {

    private ImageView tween_ain_img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tween_ani);
        tween_ain_img = (ImageView) findViewById(R.id.tween_ani);
//        方式一:加载xml动画
//        Animation animation = AnimationUtils.loadAnimation(this, R.anim.tween_ani_test);
//        animation.setDuration(5000); //动画时间
//        animation.setFillAfter(true); //true保持结束状态
//        tween_ain.startAnimation(animation);

//        方式二:代码动态写动画
        beginAnimation();

    }

    private void beginAnimation() {
        //缩放
//        ScaleAnimation animation = new ScaleAnimation(0.5f, 1.5f, 0.5f, 1.5f);

        //渐变
//        AlphaAnimation animation = new AlphaAnimation(0.3f, 1f);

        //旋转
//        RotateAnimation animation = new RotateAnimation(0,360
//                //RELATIVE_TO_SELF，RELATIVE_TO_PARENT，ABSOLUTE，分别是相对自己，相对父控件，绝对位置
//                ,Animation.RELATIVE_TO_SELF,0.5F
//                ,Animation.RELATIVE_TO_SELF,0.5f);

//        //移动
//        TranslateAnimation animation = new TranslateAnimation(0, 300, 0, 400);
//        animation.setDuration(3000);
//        animation.setFillAfter(true);
//        animation.setRepeatCount(Animation.INFINITE);

        /**-----------------------------------以下是单个动画的组合-------------------------------------------------*/

        //组合动画
        final AnimationSet animationSet = new AnimationSet(false);

        //缩放
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.5f, 1.5f, 0.5f, 1.5f);
        scaleAnimation.setDuration(3000);
        //渐变
        AlphaAnimation alphaAnimation = new AlphaAnimation(0.3f, 1f);
        alphaAnimation.setDuration(3000);
        //旋转
        RotateAnimation rotateAnimation = new RotateAnimation(0, 360
                //RELATIVE_TO_SELF，RELATIVE_TO_PARENT，ABSOLUTE，分别是相对自己，相对父控件，绝对位置
                , Animation.RELATIVE_TO_SELF, 0.5F
                , Animation.RELATIVE_TO_SELF, 0.5f);
        rotateAnimation.setDuration(3000);
//        //移动
        TranslateAnimation translateAnimation = new TranslateAnimation(0, 300, 0, 400);
        translateAnimation.setDuration(3000);

        animationSet.addAnimation(scaleAnimation);
        animationSet.addAnimation(alphaAnimation);
        animationSet.addAnimation(translateAnimation);
        animationSet.addAnimation(rotateAnimation);
        animationSet.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                tween_ain_img.startAnimation(animationSet);
            }

            //动画重复
            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tween_ain_img.startAnimation(animationSet);
    }
}
