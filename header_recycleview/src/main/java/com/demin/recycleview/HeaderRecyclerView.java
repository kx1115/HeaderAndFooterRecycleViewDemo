package com.demin.recycleview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class HeaderRecyclerView extends RecyclerView {
    private static final String TAG = "HeaderRecyclerView";
    //头view的集合
    private ArrayList<HeaderViewHolder> mHeaderViewInfos=new ArrayList<>();
    //脚view的集合
    private ArrayList<HeaderViewHolder> mFooterViewInfos=new ArrayList<>();

    private Adapter mAdapter;

    public HeaderRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }
    public void addHeaderView(View v) {
        mHeaderViewInfos.add(new HeaderViewHolder(v));
        // 如果适配器未初始
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    public void addFooterView(View v) {
        mFooterViewInfos.add(new HeaderViewHolder(v));
        // 如果适配器未初始
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }
        }
    }

    public void setEmptyView(View view){
        // 如果适配器未初始
        if (mAdapter != null) {
            if (!(mAdapter instanceof HeaderViewRecyclerAdapter)) {
                mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, mAdapter);
            }

            HeaderViewRecyclerAdapter ha= (HeaderViewRecyclerAdapter)mAdapter;
            ha.setEmptyView(view);
            mAdapter.notifyDataSetChanged();
        }else{
            Log.e(TAG,"before call the setEmptyView should set Adapter");
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViewInfos.size() > 0|| mFooterViewInfos.size() > 0) {
            mAdapter = new HeaderViewRecyclerAdapter(mHeaderViewInfos, mFooterViewInfos, adapter);
        } else {
            mAdapter = adapter;
        }
        super.setAdapter(mAdapter);
    }

}
