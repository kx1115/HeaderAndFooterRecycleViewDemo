package com.yus.taobaoui;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yus.taobaoui.util.UIUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**Rv适配器，包含多个头布局
 * Created by yusheng on 2016/11/28.
 */
public class RvAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {
    /**普通商品的（normalHolder）的标题集合,调用者传入*/
    private List<String> normalGoodsTitls;

    public RvAdapter(List<String> normalGoodsTitls) {
        this.normalGoodsTitls = normalGoodsTitls;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NormalHolder(View.inflate(UIUtils.getContext(), R.layout.rv_item_normal, null));
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
            NormalHolder normalHolder= (NormalHolder) holder;
            final int realPostion=position;//-headArr.size();//获取真正的位置

            if (mOnItemClickLitener != null) {
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemClick(holder.itemView, pos);
                    }
                });
                holder.itemView.setOnLongClickListener(new View.OnLongClickListener()
                {
                    @Override
                    public boolean onLongClick(View v)
                    {
                        int pos = realPostion;
                        mOnItemClickLitener.onItemLongClick(holder.itemView, pos);
                        return true;
                    }
                });
            }

//          Log.d("alan","位置-->"+position);
            normalHolder.tv_title.setText(normalGoodsTitls.get(realPostion));
            normalHolder.iv_goodsLeft.setOnClickListener(this);
            normalHolder.iv_goodsLeft.setTag(realPostion);
            normalHolder.iv_goodsRight.setOnClickListener(this);
            normalHolder.iv_goodsRight.setTag(realPostion);
    }



    //有7条普通数据，但是要加上Header的总数
    @Override
    public int getItemCount() {
        return normalGoodsTitls.size();
    }

    /*public int getHeaderCount(){
        return headArr.size();
    }*/

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.iv_goodsLeft:
                int postion= (int) v.getTag();
                UIUtils.showToast("shoes left"+"\n"+"postion "+postion);
                 break;
            case R.id.iv_goodsRight:
                int postion2= (int) v.getTag();
                UIUtils.showToast("shoes right"+"\n"+"postion "+postion2);
                break;
        }
    }

    //顶部banner
    class HeaderHolder extends RecyclerView.ViewHolder {

        public HeaderHolder(View itemView) {
            super(itemView);
            //ButterKnife.bind(this, itemView);
        }
    }

    //普通的Holder
    class NormalHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_goodsLeft)
        ImageView iv_goodsLeft;
        @BindView(R.id.iv_goodsRight)
        ImageView iv_goodsRight;
        @BindView(R.id.tv_title)
        TextView tv_title;
        public NormalHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(View v,int postion);
        void onItemLongClick(View v,int postion);
    }
    /**自定义条目点击监听*/
    private OnItemClickListener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickListener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }
}
