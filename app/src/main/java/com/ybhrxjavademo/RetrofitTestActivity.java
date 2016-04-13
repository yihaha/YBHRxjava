package com.ybhrxjavademo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.ybhrxjavademo.bean.MeiziDetail;
import com.ybhrxjavademo.bean.MeiziResult;
import com.ybhrxjavademo.http.RetrofitY;

import java.util.ArrayList;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class RetrofitTestActivity extends AppCompatActivity {

    private RecyclerView mRecycleview;
    private MeiziListAdapter meiziListAdapter;
    private SwipeRefreshLayout mSwiReLayout;
    public ArrayList<MeiziDetail> meiziDetails1;
    private int nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_test);
        nextPage = 1;
        meiziDetails1 = new ArrayList<>();
        initView();
    }

    private void initView() {
        mSwiReLayout = (SwipeRefreshLayout) findViewById(R.id.swiprefreshlayout);
        mRecycleview = (RecyclerView) findViewById(R.id.recy_id);
        meiziListAdapter = new MeiziListAdapter(RetrofitTestActivity.this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(RetrofitTestActivity.this);
        mRecycleview.setLayoutManager(linearLayoutManager);
        mRecycleview.setHasFixedSize(true);
        mRecycleview.setAdapter(meiziListAdapter);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwiReLayout.setRefreshing(true);
                loadData(nextPage);
            }
        }, 500);

        mSwiReLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                nextPage = 1;
                loadData(nextPage);
            }
        });

        mRecycleview.addOnScrollListener(getScrollToBottomLisener(linearLayoutManager));

    }

    private RecyclerView.OnScrollListener getScrollToBottomLisener(final LinearLayoutManager linearLayoutManager) {
        return new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                boolean isBottom = linearLayoutManager.findLastVisibleItemPosition() == meiziDetails1.size() - 1;
                if (!mSwiReLayout.isRefreshing() && isBottom) {
                    mSwiReLayout.setRefreshing(true);
                    nextPage++;
                    loadData(nextPage);
                }
            }
        };
    }


    private void loadData(final int... page) {
        RetrofitY.getMeiziApi()
                .getMeiziList(page[0])
                .map(new Func1<MeiziResult, ArrayList<MeiziDetail>>() {
                    @Override
                    public ArrayList<MeiziDetail> call(MeiziResult meiziResult) {
                        return meiziResult.results;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<MeiziDetail>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mSwiReLayout.setRefreshing(false);
                    }

                    @Override
                    public void onNext(ArrayList<MeiziDetail> meiziDetails) {
                        if (page[0]==1&&meiziDetails1.size()>0){
                            meiziDetails1.clear();
                        }
                        mSwiReLayout.setRefreshing(false);
                        meiziDetails1.addAll(meiziDetails);
                        meiziListAdapter.setRefresh(meiziDetails1);
                    }
                });
    }
}
