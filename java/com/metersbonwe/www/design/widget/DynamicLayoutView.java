package com.metersbonwe.www.design.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.metersbonwe.www.design.MainActivity;
import com.metersbonwe.www.design.R;

/**
 * Created by joejoe on 2014/8/9.
 */
public class DynamicLayoutView extends LinearLayout {


    Context ctx;
    MainActivity activity;

    public DynamicLayoutView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.ctx = context;
        activity = (MainActivity) ctx;
        this.setClickable(true);

    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();


    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();


        TextView backAction = (TextView) this.findViewById(R.id.back_action);
        TextView actionTitle = (TextView) this.findViewById(R.id.action_title);
        TextView rightAction = (TextView) this.findViewById(R.id.right_action);

        backAction.setVisibility(View.GONE);
        rightAction.setVisibility(View.GONE);

    }
}
