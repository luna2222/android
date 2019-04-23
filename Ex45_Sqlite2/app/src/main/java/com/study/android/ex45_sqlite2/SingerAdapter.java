package com.study.android.ex45_sqlite2;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import java.util.ArrayList;

public class SingerAdapter extends BaseAdapter {

    Context context;
    ArrayList<SingerItem> items= new ArrayList<>();

    public SingerAdapter(Context context){
        this.context= context;
    }
    public  void addItem(SingerItem item){
        items.add(item);

    }
    @Override
    public  int getCount(){
        return  items.size();
    }

    @Override
    public  Object getItem(int position){
        return  items.size();
    }
    @Override
    public  long getItemId(int position){
        return  position;
    }
    @Override
    public View getView(int position, View converView, ViewGroup parent){
        SingerItemView view= null;
        if(converView==null){
            view =new SingerItemView(context);

        }else {
            view=(SingerItemView)converView;
        }
        final  SingerItem item= items.get(position);
        view.setName(item.getName());
        view.setAge(item.getAge());
        view.setMobile(item.getMobile());


        return view;
    }
}


