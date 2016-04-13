package com.ybhrxjavademo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;


import uk.co.senab.photoview.PhotoView;

public class ShowPicActivity extends AppCompatActivity {

    private String imgUrl;
    private PhotoView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_pic);
        img = (PhotoView) findViewById(R.id.show_img_id);

        imgUrl = getIntent().getStringExtra("imgUrl");
        Glide.with(ShowPicActivity.this).load(imgUrl).into(img);
//        Picasso.with(ShowPicActivity.this).load(imgUrl).into(img);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
