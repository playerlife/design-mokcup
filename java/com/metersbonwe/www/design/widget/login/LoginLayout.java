package com.metersbonwe.www.design.widget.login;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.metersbonwe.www.design.MainActivity;
import com.metersbonwe.www.design.R;

/**
 * Created by joejoe on 2014/8/11.
 */
public class LoginLayout extends LinearLayout {

    MainActivity activity;
    Context ctx;

    public LoginLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        ctx = context;
        activity = (MainActivity) context;
    }


    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        // 点击登录按钮
        this.findViewById(R.id.btn_login).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation anim = AnimationUtils.loadAnimation(activity, R.anim.left_out);
                LoginLayout.this.startAnimation(anim);


                anim.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart(Animation animation) {

                    }

                    @Override
                    public void onAnimationEnd(Animation animation) {
                        /**动画结束后 移除activity中的登录界面*/
                        FrameLayout parent = (FrameLayout) LoginLayout.this.getParent();
                        if (parent != null) {
                            parent.removeView(LoginLayout.this);
                        }
                    }

                    @Override
                    public void onAnimationRepeat(Animation animation) {

                    }
                });
            }
        });

    } // end onFinishInflate


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        /**点击空白处软键盘消失*/
        InputMethodManager imm = (InputMethodManager) ctx.getSystemService(ctx.INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        // 阻止事件穿透
        return true;
    }

}
