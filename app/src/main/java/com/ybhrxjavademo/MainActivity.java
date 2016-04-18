package com.ybhrxjavademo;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.ybhrxjavademo.animation.AnimationTestActivity;
import com.ybhrxjavademo.bean.YUser;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText ed_1;
    private Button bt_1;
    private ImageView img_1;
    private TextView text_1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        ed_1 = (EditText) findViewById(R.id.ed_1);
        bt_1 = (Button) findViewById(R.id.but_1);
        img_1 = (ImageView) findViewById(R.id.img_1);
        text_1 = (TextView) findViewById(R.id.text_1);
        //进入动画页
        findViewById(R.id.animation_id).setOnClickListener(this);
        findViewById(R.id.next_bt).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RetrofitTestActivity.class));
            }
        });
        bt_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                testObAndable();
//                testObAndable2();
//                testObservable();
//                testThread();
//                testMap();
//                testFlatMap();

                startActivity(new Intent(MainActivity.this, ShowViewActivity.class));

            }
        });

//        Observable.timer(5, TimeUnit.SECONDS)
//                .subscribe(new Subscriber<Long>() {
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//
//                    }
//
//                    @Override
//                    public void onNext(Long aLong) {
//                        Log.w("时间",aLong+"时间呀0");
//                        startActivity(new Intent(MainActivity.this, RetrofitTestActivity.class));
//                    }
//                });

    }

    private void testObAndable() {
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.w("observer", s);
            }
        };

        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("android");
                subscriber.onNext("ios");
                subscriber.onNext("html5");
                subscriber.onCompleted();
            }
        });

        observable.subscribe(observer); //订阅

    }

    private void testObAndable2() {
        Subscriber<String> subscriber = new Subscriber<String>() {

            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.w("subscriber", s);
            }
        };

        Observable<String> just = Observable.just("android", "ios", "html5");
        Subscription subscribe = just.subscribe(subscriber);//订阅,,subscriber会返回Subscription,然后可以判断是否已经订阅,也能进行取消订阅
//        subscribe.unsubscribe();//取消订阅
//        boolean unsubscribed = subscribe.isUnsubscribed();//判断是否订阅


       /*  和上面一样
       String [] ss={"android","ios","html5"};
        Observable<String> from = Observable.from(ss);
        from.subscribe(subscriber);
        */

    }


    private void testObservable() {
//        String s1 = ed_1.getText().toString();
        String[] s1 = new String[]{"1", "3", "5", "7", "9"};
        Observable.from(s1).subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                Log.w("ed_log", s);
            }
        });
    }

    //线程测试
    private void testThread() {
        final int drawablesId = R.mipmap.test1;
        Observable.create(new Observable.OnSubscribe<Drawable>() {
            //                    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void call(Subscriber<? super Drawable> subscriber) {
//                        Drawable drawable = getTheme().getDrawable(drawablesId);
                Drawable drawable = getApplicationContext().getResources().getDrawable(drawablesId);
                subscriber.onNext(drawable);
                subscriber.onCompleted();
            }
        }).subscribeOn(Schedulers.io()) //subscribe所在的线程
                .observeOn(AndroidSchedulers.mainThread()) //subscribe的回调所在的线程
                .subscribe(new Subscriber<Drawable>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Drawable drawable) {
                        img_1.setImageDrawable(drawable);
                    }
                })
        ;
    }

    /**
     * 转换,一对一;将YUser对象转换成String
     */
    private void testMap() {
        YUser yUser = new YUser();
        yUser.userName = "ybh";
        yUser.email = ed_1.getText().toString();

        Observable.just(yUser)
                .map(new Func1<YUser, String>() {
                    @Override
                    public String call(YUser yUser) {
                        return yUser.email;
                    }
                })
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        text_1.setText(s);
                    }
                });
    }

    /**
     * 打印每个学生的课程名称
     */
    private void testFlatMap() {
        YUser[] users = new YUser[5];
        for (int i = 0; i < users.length; i++) {
            YUser yUser = new YUser();
            final String[] courses = new String[3];
            courses[0] = "数学";
            courses[1] = "英语";
            courses[2] = "语文";
            yUser.courses = courses;
            users[i] = yUser;
        }
        Observable.from(users)
                .flatMap(new Func1<YUser, Observable<String>>() {

                    @Override
                    public Observable<String> call(YUser yUser) {
                        return Observable.from(yUser.courses);
                    }
                }).subscribe(new Subscriber<String>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                Log.w("课程", s);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.animation_id:
                startActivity(new Intent(MainActivity.this, AnimationTestActivity.class));
                break;

            default:

                break;
        }
    }
}
