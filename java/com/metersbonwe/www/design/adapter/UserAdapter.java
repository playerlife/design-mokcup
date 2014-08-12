package com.metersbonwe.www.design.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.metersbonwe.www.design.R;
import com.metersbonwe.www.design.entity.User;

import java.util.ArrayList;

/**
 * Created by joejoe on 2014/8/8.
 */
public class UserAdapter extends BaseAdapter {

    private Context ctx;


    private ArrayList<User> userList = new ArrayList<User>();




    public UserAdapter(Context context) {


        this.ctx = context;

        for (int i = 0 ; i < 20; i++) {
            User user =  new User();
            user.setUserName("这是用户" + i);
            user.setContent("这是列表项" + i);
            userList.add(user);
        }



    }


    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = null;

        if (convertView != null) {
            view = convertView;
        } else {
            view = LayoutInflater.from(ctx).inflate(R.layout.adapter_user, null);
        }

        TextView user = (TextView) view.findViewById(R.id.user);
        TextView content = (TextView) view.findViewById(R.id.detail);

        User obj = userList.get(position);
        user.setText(obj.getUserName());
        content.setText(obj.getContent());

        return view;
    }
}
