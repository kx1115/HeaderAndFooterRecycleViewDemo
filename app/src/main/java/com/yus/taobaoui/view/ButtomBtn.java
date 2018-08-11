package com.yus.taobaoui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yus.taobaoui.R;
import com.yus.taobaoui.util.UIUtils;

/**底部按钮
 * Created by yusheng on 2016/11/28.
 */
public class ButtomBtn extends LinearLayout {

    private ImageView iv;
    private TextView tv;

    public ButtomBtn(Context context) {
        super(context);
        init(context);
    }
    public ButtomBtn(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ButtomBtn(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    private void init(Context context) {
        setOrientation(VERTICAL);
        LayoutParams layoutParams = new LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        setLayoutParams(layoutParams);
        setGravity(Gravity.CENTER);
        setPadding(UIUtils.dip2px(5),UIUtils.dip2px(5),UIUtils.dip2px(5),UIUtils.dip2px(5));

        View bottomBtnView = LayoutInflater.from(context).inflate(R.layout.bottom_btn_view, this, true);
        iv = (ImageView) bottomBtnView.findViewById(R.id.iv);
        tv = (TextView) bottomBtnView.findViewById(R.id.tv);
    }
    public void setIvAndTv(int imgRes,String tvString){
        iv.setImageResource(imgRes);
        tv.setText(tvString);
    }
    public void setTvColor(int color){
        tv.setTextColor(color);
    }

}
