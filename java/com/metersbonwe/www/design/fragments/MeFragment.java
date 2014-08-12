package com.metersbonwe.www.design.fragments;

import android.annotation.TargetApi;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.metersbonwe.www.design.R;

/**
 * Created by joejoe on 2014/8/8.
 * 我
 */
@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class MeFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me_layout, container, false);
        return view;
    }

}
