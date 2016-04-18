package com.ybhrxjavademo.animation;

import android.content.res.XmlResourceParser;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.ybhrxjavademo.R;

public class FramAnimationActivity extends AppCompatActivity implements View.OnClickListener {
    /**
     * 参考
     * http://www.cnblogs.com/plokmju/p/android_AnimationDrawable.html
     */
    private ImageView ani_img;
    private AnimationDrawable animationDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_animation);
        findViewById(R.id.start_id).setOnClickListener(this);
        findViewById(R.id.stop_id).setOnClickListener(this);
        ani_img = (ImageView) findViewById(R.id.ani_img);

        //通过Drawable文件夹里动画xml文件来获取动画
//        animationDrawable = (AnimationDrawable) getResources().getDrawable(R.drawable.tween_animation);

        //动态添加
        animationDrawable = new AnimationDrawable();
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img0),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img1),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img2),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img3),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img4),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img5),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img6),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img7),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img8),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img9),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img10),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img11),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img12),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img13),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img14),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img15),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img16),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img17),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img18),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img19),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img20),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img21),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img22),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img23),300);
        animationDrawable.addFrame(getResources().getDrawable(R.drawable.img24),300);
        animationDrawable.setOneShot(false); //false 代表不止播放一次
        ani_img.setBackgroundDrawable(this.animationDrawable);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.start_id:
                startAni();
                break;
            case R.id.stop_id:
                stopAni();
                break;

            default:
                break;
        }
    }

    private void stopAni() {
        if (animationDrawable.isRunning()){
            animationDrawable.stop();
        }
    }

    private void startAni() {
        if (!animationDrawable.isRunning()) {
            animationDrawable.start();
        }
    }
}
