package com.ybhrxjavademo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ybhrxjavademo.bean.MeiziDetail;


import java.util.ArrayList;

/**
 * Created by y on 2016/4/13.
 */
public class MeiziListAdapter extends RecyclerView.Adapter<MeiziListAdapter.MeiziListViewHolder> {
    private OnRecycleViewItemClickListener onItemClickListener;
    public Context mContext;
    private ArrayList<MeiziDetail> mList;
    private RetrofitTestActivity mActivity;
    public MeiziListAdapter(Context context) {
        this.mContext = context;
        mActivity= (RetrofitTestActivity) context;
    }

    @Override
    public MeiziListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meizilist_item, parent, false);
        return new MeiziListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziListViewHolder holder, int position) {
        MeiziDetail meiziDetail = mList.get(position);
        holder.setView(meiziDetail);
    }

    @Override
    public int getItemCount() {
//        return mList == null ? 0 : mList.size();
        return mList == null ? 0 : mList.size();
    }

    public void setRefresh(ArrayList<MeiziDetail> arrayList) {
        this.mList = arrayList;
        notifyDataSetChanged();

    }



    class MeiziListViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;
        private String IMG_URL;
        public MeiziListViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.mei_img);
            mTextView = (TextView) itemView.findViewById(R.id.img_desc);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//
//                    KShareViewActivityManager
//                            .getInstance(mActivity)
//                            .startActivity(mActivity,ShowPicActivity.class
//                                    ,R.layout.meizilist_item,R.layout.activity_show_pic,mImageView);
//                    EventBus.getDefault().post(IMG_URL);
//                }
//            });
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mActivity, ShowPicActivity.class);
                    intent.putExtra("imgUrl",IMG_URL);
                    mContext.startActivity(intent);
                    //切换动画
                    mActivity.overridePendingTransition(R.anim.slide_right_in,R.anim.slide_right_out);
                }
            });
        }

        /**
         * 设置数据
         *
         * @param meiziDetail
         */
        public void setView(MeiziDetail meiziDetail) {
            IMG_URL=meiziDetail.url;
            Glide.with(mContext)
                    .load(meiziDetail.url)
                    .placeholder(R.mipmap.test2)
                    .centerCrop()
                    .dontAnimate()
                    .into(mImageView);
            mTextView.setText(meiziDetail.desc);
        }
    }

    public interface OnRecycleViewItemClickListener {
        void onItemClick(View view,MeiziDetail meiziDetail);
    }

    public void setOnItemClickListener(OnRecycleViewItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

}
