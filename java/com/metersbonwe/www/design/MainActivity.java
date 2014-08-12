package com.metersbonwe.www.design;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.metersbonwe.www.design.fragments.BusinessFragment;
import com.metersbonwe.www.design.fragments.DynamicFragment;
import com.metersbonwe.www.design.fragments.MeFragment;
import com.metersbonwe.www.design.fragments.TrendyFragment;

import java.util.LinkedList;

/**
 * 造形师  app
 *
 * @author joejoe
 */

public class MainActivity extends ActionBarActivity implements View.OnClickListener {


    /**
     * 动态
     */
    public DynamicFragment dynamicFragment;

    /**
     * 潮流
     */
    private TrendyFragment trendyFragment;


    /**
     * 贵族图
     */
    private BusinessFragment businessFragment;

    /**
     * 我
     */
    private MeFragment meFragment;

    /**
     * 动态界面布局
     */
    private View dynamicLayout;

    /**
     * 潮流界面布局
     */
    private View trendyLayout;

    /**
     * 贵族图界面布局
     */
    private View businessLayout;

    /**
     * 我界面布局
     */
    private View meLayout;

    /**
     * 用于对Fragment进行管理
     */
    private FragmentManager fragmentManager;

    /**
     * 模块列表
     */
    private View dyamic, trendy, busines, me;


    /**
     * 动画模块
     */
    public Animation leftIn, leftOut, rightIn, rightOut, bottomEnter, bottomOut;
    /**
     * 模块选中文字
     */
    private TextView dynamicText, trendyText, businesText, meText;


    /**
     * 模块中的图片
     */
    private ImageView dynamicImage, trendyImage, businesImage, meImage;

    /**
     * 当前处理激活状态的fragment
     * @param savedInstanceState
     */
    public Fragment currentFragment;


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();

        fragmentManager = getFragmentManager();

        showDynamicFragmentFirst();

        loadAnim();
    }


    private void loadAnim() {

        leftIn = AnimationUtils.loadAnimation(this, R.anim.left_in);
        leftOut = AnimationUtils.loadAnimation(this, R.anim.left_out);
        rightIn = AnimationUtils.loadAnimation(this, R.anim.right_in);
        rightOut = AnimationUtils.loadAnimation(this, R.anim.right_out);
        bottomEnter = AnimationUtils.loadAnimation(this, R.anim.dialog_enter);
        bottomOut = AnimationUtils.loadAnimation(this, R.anim.dialog_exit);
    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void showDynamicFragmentFirst() {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        dynamicFragment = new DynamicFragment();
        transaction.add(R.id.content, dynamicFragment);
        transaction.commit();

        currentFragment = dynamicFragment;

    }


    private void findViews() {

        dyamic = findViewById(R.id.dynamic);
        trendy = findViewById(R.id.trendy);
        busines = findViewById(R.id.busines);
        me = findViewById(R.id.me);

        dynamicText = (TextView) findViewById(R.id.dynamic_text);
        trendyText = (TextView) findViewById(R.id.trendy_text);
        businesText = (TextView) findViewById(R.id.busines_text);
        meText = (TextView) findViewById(R.id.me_text);

        dynamicImage = (ImageView) findViewById(R.id.dynamic_image);
        trendyImage = (ImageView) findViewById(R.id.trendy_image);
        businesImage = (ImageView) findViewById(R.id.busines_image);
        meImage = (ImageView) findViewById(R.id.me_image);

        dyamic.setOnClickListener(this);
        trendy.setOnClickListener(this);
        busines.setOnClickListener(this);
        me.setOnClickListener(this);

    }


    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public void onClick(View v) {

        clearSelectStatus();

        FragmentTransaction transaction = fragmentManager.beginTransaction();

        // 先隐藏掉所有的Fragment，以防止有多个Fragment显示在界面上的情况

        hideFragments(transaction);

        switch (v.getId()) {

            case R.id.dynamic:
                dynamicImage.setImageResource(R.drawable.dynamic_selected);
                dynamicText.setTextColor(Color.RED);

                if (dynamicFragment == null) {
                    dynamicFragment = new DynamicFragment();
                    transaction.add(R.id.content, dynamicFragment);
                } else {
                    transaction.show(dynamicFragment);
                }
                currentFragment = dynamicFragment;

                break;
            case R.id.trendy:
                trendyImage.setImageResource(R.drawable.trendy_selected);
                trendyText.setTextColor(Color.RED);

                break;
            case R.id.busines:
                businesImage.setImageResource(R.drawable.busines_selected);
                businesText.setTextColor(Color.RED);
                break;
            case R.id.me:
                meImage.setImageResource(R.drawable.me_selected);
                meText.setTextColor(Color.RED);
                break;
        }
        // transaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        transaction.commit();
    }

    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void hideFragments(FragmentTransaction transaction) {

        if (dynamicFragment != null) {
            transaction.hide(dynamicFragment);
        }
        if (trendyFragment != null) {
            transaction.hide(trendyFragment);
        }
        if (businessFragment != null) {
            transaction.hide(businessFragment);
        }
        if (meFragment != null) {
            transaction.hide(meFragment);
        }
    }

    private void clearSelectStatus() {

        dynamicImage.setImageResource(R.drawable.dynamic);
        dynamicText.setTextColor(Color.parseColor("#82858b"));
        trendyImage.setImageResource(R.drawable.trendy);
        trendyText.setTextColor(Color.parseColor("#82858b"));
        businesImage.setImageResource(R.drawable.busines);
        businesText.setTextColor(Color.parseColor("#82858b"));
        meImage.setImageResource(R.drawable.me);
        meText.setTextColor(Color.parseColor("#82858b"));
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {

            case KeyEvent.KEYCODE_BACK:

                if (currentFragment == dynamicFragment && dynamicFragment.linkedList.size() > 1) {
                    dynamicFragment.fadeBack(null);
                } else {
                    exitApp();
                }

                break;
        }
        return true;
    }

    private void exitApp() {

        new AlertDialog.Builder(MainActivity.this)
                .setTitle("是否退出应用?")
                .setNegativeButton("是", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MainActivity.this.finish();
                    }
                })
                .setPositiveButton("否", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                })
                .show();
    }


}
