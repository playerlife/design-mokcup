package com.metersbonwe.www.design.fragments;

import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.metersbonwe.www.design.MainActivity;
import com.metersbonwe.www.design.R;
import com.metersbonwe.www.design.adapter.UserAdapter;

import java.util.LinkedList;

/**
 * Created by joejoe on 2014/8/8.
 * 动态
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class DynamicFragment extends Fragment {


    /**
     * 维护一个view的回退栈
     */
    public LinkedList<View> linkedList = new LinkedList<View>();

    /**
     * 动画切换器
     */
    public ViewFlipper viewFlipper;

    /**
     * 当前的activity
     */
    public MainActivity activity;

    /**
     * 动画是否正在播放中
     */
    private boolean isPlaying = true;

    /**
     * fragment容器
     */
    FrameLayout rootFrame;

    /**
     * fragment容器
     */
    FrameLayout content;

    /**
     * content 布局参数
     */
    FrameLayout.LayoutParams frameLayoutParams;

    /**
     * 底部切换项
     */
    LinearLayout fragmentsItem;


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        activity = (MainActivity) getActivity();
        rootFrame = (FrameLayout) getActivity().findViewById(R.id.root_frame);
        content = (FrameLayout) getActivity().findViewById(R.id.content);
        fragmentsItem = (LinearLayout) getActivity().findViewById(R.id.fragments_item);
        fragmentsItem.setGravity(Gravity.BOTTOM);
        frameLayoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT);

        LinearLayout layout = (LinearLayout) inflater.inflate(R.layout.dynamic_list_view, null);
        ListView listView = (ListView) layout.findViewById(R.id.dynamic_list_view);
        listView.setAdapter(new UserAdapter(getActivity()));

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                frameLayoutParams.setMargins(0, 0, 0, 0);
                content.setLayoutParams(frameLayoutParams);

                View v = rootFrame.getChildAt(0);
                rootFrame.bringChildToFront(v);

                fragmentsItem.startAnimation(activity.bottomOut);

                switch (position) {
                    case 0:
                        View detail = inflater.inflate(R.layout.dynamic_user_detail, null);
                        viewFlipper.addView(detail);
                        linkedList.add(detail);
                        break;
                    case 1:
                        View detail2 = inflater.inflate(R.layout.dynamic_user_detail3, null);
                        viewFlipper.addView(detail2);
                        linkedList.add(detail2);
                        break;
                }
                viewFlipper.setInAnimation(activity.leftIn);
                viewFlipper.setOutAnimation(activity.leftOut);
                viewFlipper.showNext();
            }
        });

        viewFlipper = (ViewFlipper) inflater.inflate(R.layout.dynamic_layout, null);
        viewFlipper.addView(layout);
        linkedList.add(layout);

        return viewFlipper;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 回退动画
     *
     * @param v
     */

    public void fadeBack(View v) {

        if (isPlaying == false) return;

        final int size = linkedList.size();

        /** 如果只有一个元素 则表示到达根结点 不能再回退*/
        if (size == 1) {
            return;
        }

        if (size == 2) {
            View v2 = rootFrame.getChildAt(0);
            rootFrame.bringChildToFront(v2);

            fragmentsItem.startAnimation(activity.bottomEnter);


        }

        // 出栈
        final View view = linkedList.pollLast();

        viewFlipper.setInAnimation(activity.rightIn);
        viewFlipper.setOutAnimation(activity.rightOut);
        viewFlipper.showPrevious();

        activity.rightOut.setAnimationListener(new Animation.AnimationListener() {


            @Override
            public void onAnimationStart(Animation animation) {
                /** 如果正在播放 则禁止回退 */
                isPlaying = false;
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // 动画结束后移除栈顶view
                viewFlipper.removeView(view);
                isPlaying = true;

                /** 如果退到根节点 把content下边距上移 */
                if (size == 2) {
                    frameLayoutParams.setMargins(0, 0, 0, 80);
                    content.setLayoutParams(frameLayoutParams);
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }
}
