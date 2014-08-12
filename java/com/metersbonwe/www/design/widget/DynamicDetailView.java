package com.metersbonwe.www.design.widget;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.metersbonwe.www.design.MainActivity;
import com.metersbonwe.www.design.R;

/**
 * Created by joejoe on 2014/8/9.
 */
public class DynamicDetailView extends LinearLayout {


    Context ctx;
    MainActivity activity;

    public DynamicDetailView(Context context, AttributeSet attrs) {
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


        final LinearLayout layout = (LinearLayout) activity.getLayoutInflater().inflate(R.layout.dynamic_setting, null);
        final AlertDialog mDialog;
        mDialog = new AlertDialog.Builder(activity, R.style.dialog).create();
        final DisplayMetrics dm = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(dm);
        final int width = dm.widthPixels;
        final Window win = mDialog.getWindow();
        final WindowManager manager = win.getWindowManager();
        manager.getDefaultDisplay().getMetrics(dm);
        win.setGravity(Gravity.BOTTOM);  //此处可以设置dialog显示的位置
        win.setWindowAnimations(R.style.dialog_fade_out);  //添加动画


        /**
         * 右侧设置项
         */
        rightAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.show();
                win.setContentView(layout);
                win.setLayout(width, LinearLayout.LayoutParams.WRAP_CONTENT);
            }
        });


        layout.findViewById(R.id.button_sure).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mDialog.dismiss();
                Toast.makeText(activity, "hoooooo", Toast.LENGTH_LONG).show();
            }
        });

        /**
         * 回退按钮
         */
        backAction.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.dynamicFragment.fadeBack(null);
            }
        });


        this.findViewById(R.id.btn_forward).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                View layout = activity.getLayoutInflater().inflate(R.layout.dynamic_user_detail2, null);
                ViewFlipper viewFlipper = activity.dynamicFragment.viewFlipper;
                viewFlipper.addView(layout);

                /** 入栈 */
                activity.dynamicFragment.linkedList.add(layout);

                viewFlipper.setInAnimation(activity.leftIn);
                viewFlipper.setOutAnimation(activity.leftOut);
                viewFlipper.showNext();
            }
        });


    }
}
