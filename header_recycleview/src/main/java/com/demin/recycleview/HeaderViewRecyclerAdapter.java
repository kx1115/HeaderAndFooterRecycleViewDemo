package com.demin.recycleview;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import java.util.ArrayList;
public class HeaderViewRecyclerAdapter extends RecyclerView.Adapter {
    /**
     *  头条目返回大于0的数据，尾显示小于0的数据类型
     */

    private ArrayList<HeaderViewHolder> mHeaderViews;
    private ArrayList<HeaderViewHolder> mFooterViews;
    private RecyclerView.Adapter mAdapter;

    public HeaderViewRecyclerAdapter(ArrayList<HeaderViewHolder> haderViews, ArrayList<HeaderViewHolder> footerViews, RecyclerView.Adapter mAdapter) {
        if (haderViews == null) {
            mHeaderViews = new ArrayList<HeaderViewHolder>();
        } else {
            mHeaderViews = haderViews;
        }

        if (footerViews == null) {
            mFooterViews = new ArrayList<HeaderViewHolder>();
        } else {
            mFooterViews = footerViews;
        }
        this.mAdapter=mAdapter;
    }

    @Override
    public int getItemViewType(int position) {
        //header 类型为正数，通过type可以换算出position
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return position+1;
        }
        // Adapter 正常为0
        final int realPosition = position - numHeaders;
        int realAdapterCount = 0;
        if (mAdapter != null) {
            realAdapterCount = mAdapter.getItemCount();
            if (realPosition < realAdapterCount) {
                return mAdapter.getItemViewType(realPosition);
            }
        }
        // Footer 用负数表示类型
        return  -(position-numHeaders-realAdapterCount)-1;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int index;
        if(viewType==0)return mAdapter.onCreateViewHolder(parent,viewType);
        else if(viewType>0){
            index=viewType-1;
            return mHeaderViews.get(index);
        }
        else{
            index=index=-viewType-1;
            return mFooterViews.get(index);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //也要划分三个区域
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {//是头部
            return;
        }
        //原来的adapter正常内容
        final int realPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (realPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, realPosition);
                return;
            }
        }
        //尾部

    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    /**
     *
     * @return Header的个数
     */
    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    /**
     * footer的个数
     * @return
     */
    public int getFootersCount() {
        return mFooterViews.size();
    }


}
