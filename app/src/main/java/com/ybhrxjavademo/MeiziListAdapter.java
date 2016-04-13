package com.ybhrxjavademo;

import android.content.Context;
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
    public Context mContext;
    private ArrayList<MeiziDetail> mList;

    public MeiziListAdapter(Context context) {
        this.mContext = context;
    }

    @Override
    public MeiziListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.meizilist_item, parent, false);
        return new MeiziListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MeiziListViewHolder holder, int position) {
        holder.setView(mList.get(position));
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

        public MeiziListViewHolder(View itemView) {
            super(itemView);
            mImageView = (ImageView) itemView.findViewById(R.id.mei_img);
            mTextView= (TextView) itemView.findViewById(R.id.img_desc);
        }

        public void setView(MeiziDetail meiziDetail) {
            Glide.with(mContext)
                    .load(meiziDetail.url)
                    .placeholder(R.mipmap.test1)
                    .centerCrop()
                    .into(mImageView);
            mTextView.setText(meiziDetail.desc);
        }
    }
}
