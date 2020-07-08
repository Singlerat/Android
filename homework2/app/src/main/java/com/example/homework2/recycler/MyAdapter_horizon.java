package com.example.homework2.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.homework2.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.ArrayList;
import java.util.List;

public class MyAdapter_horizon extends RecyclerView.Adapter<MyAdapter_horizon.MyViewHolder> {
    private List<TestData> mDataset = new ArrayList<>();
    private MyAdapter.IOnItemClickListener mItemClickListener;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvTime;
        //private  ImageView tvImage;
        private View contentView;


        public MyViewHolder(View v) {
            super(v);
            contentView = v;
            //tvImage=v.findViewById(R.id.tv_image_vertical);
            tvTitle = v.findViewById(R.id.tv_title_horizon);
            tvTime = v.findViewById(R.id.tv_time_horizon);

        }


        public void onBind(int position, TestData data) {
            //tvImage.setImageResource(R.mipmap.ic_launcher);
            tvTitle.setText(data.title);
            //tvTime.setText(data.time);
        }

        public void setOnClickListener(View.OnClickListener listener) {
            if (listener != null) {
                contentView.setOnClickListener(listener);
            }
        }

        public void setOnLongClickListener(View.OnLongClickListener listener) {
            if (listener != null) {
                contentView.setOnLongClickListener(listener);
            }
        }
    }


    public MyAdapter_horizon(List<TestData> myDataset) {
        mDataset.addAll(myDataset);

    }

    public void setOnItemClickListener(MyAdapter.IOnItemClickListener listener) {
        mItemClickListener = listener;
    }

    public void addData(int position, TestData data) {
        mDataset.add(position, data);
        notifyItemInserted(position);
        if (position != mDataset.size()) {
            //刷新改变位置item下方的所有Item的位置,避免索引错乱
            notifyItemRangeChanged(position, mDataset.size() - position);
        }
    }

    public void removeData(int position) {
        if (null != mDataset && mDataset.size() > position) {
            mDataset.remove(position);
            notifyItemRemoved(position);
            if (position != mDataset.size()) {
                //刷新改变位置item下方的所有Item的位置,避免索引错乱
                notifyItemRangeChanged(position, mDataset.size() - position);
            }
        }
    }

    @Override
    public MyAdapter_horizon.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                     int viewType) {
        return new MyAdapter_horizon.MyViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main_horizon, parent, false));
    }


    @Override
    public void onBindViewHolder(MyAdapter_horizon.MyViewHolder holder, final int position) {
        holder.onBind(position, mDataset.get(position));
        holder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemCLick(position, mDataset.get(position));
                }
            }
        });
        holder.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mItemClickListener != null) {
                    mItemClickListener.onItemLongCLick(position, mDataset.get(position));
                }
                return false;
            }

        });
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mDataset.size();
    }

    public interface IOnItemClickListener {

        void onItemCLick(int position, TestData data);

        void onItemLongCLick(int position, TestData data);
    }
}