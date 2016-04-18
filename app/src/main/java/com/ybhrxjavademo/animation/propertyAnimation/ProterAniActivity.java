package com.ybhrxjavademo.animation.propertyAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.support.v4.animation.ValueAnimatorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.AnimationSet;
import android.widget.ImageView;

import com.ybhrxjavademo.R;

public class ProterAniActivity extends AppCompatActivity {

    private ImageView proper_img;

    /**
     * 参考: http://blog.csdn.net/guolin_blog/article/details/43536355
     * 属性动画可以实现补间动画的功能
     * 与补间动画相比:
     *      补间动画存在以下缺陷:
     *                1:补间动画不能动态实时对view背景色进行改变
     *                2:只改变了view的显示效果,实际属性并没改变;例如将左上角的button移动到右下角,
     *                点击事件还在左上角,
     *                3:只对view有效果
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proter_ani);
        proper_img = (ImageView) findViewById(R.id.proper_img);
//        testValueAnimator();
//        testObjectAnimation1();
        testObjectAnimation2();
    }

    private void testObjectAnimation2() {

    }

    private void testObjectAnimation1() {
        //旋转
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(proper_img,"rotation",0f,180f);
        //缩放
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(proper_img,"scaleY",1f,1.5f,0.5f);
        //移动
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(proper_img
//                ,"translationX",proper_img.getTranslationX(),400f,proper_img.getTranslationX());
//        //渐变
//        ValueAnimator valueAnimator = ObjectAnimator.ofFloat(proper_img,"alpha",0.5f,1f);
//
//        valueAnimator.setDuration(2000);
//        valueAnimator.start();

        /**-----------------------组合动画----------------------***/
        //旋转
        ValueAnimator ani1 = ObjectAnimator.ofFloat(proper_img,"rotation",0f,360f);
        //缩放
        ValueAnimator ani2 = ObjectAnimator.ofFloat(proper_img,"scaleY",1f,1.5f,0.5f);
        //移动
        ValueAnimator ani3 = ObjectAnimator.ofFloat(proper_img
                ,"translationX",proper_img.getTranslationX(),400f,proper_img.getTranslationX());
        //渐变
        ValueAnimator ani4 = ObjectAnimator.ofFloat(proper_img,"alpha",0.5f,1f);

        AnimatorSet animatorSet = new AnimatorSet();
        //with同时进行
        //下面动画的效果是先进行缩放,然后旋转和移动同时进行,最后是渐变(以实际效果为准)
        animatorSet.play(ani1).after(ani2).with(ani3).before(ani4);
        animatorSet.setDuration(5000);
        animatorSet.start();

//        animatorSet.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animation) {
//
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animation) {
//
//            }
//        });
        //和上面效果相同,只是不需要每个方法都写,可以根据需要来
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }
        });

    }

    private void testValueAnimator() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0f,1f);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.w("aniUpListener",animation.getAnimatedValue()+"");
            }
        });
        valueAnimator.start();
    }

}
