package com.study.android.ex24_fragment2;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class ViewerFragment extends Fragment {
        private  static  final String TAG="lecture";
        ImageView imageView;
        @Nullable
        @Override
    public ViewerFragment(LayoutInflater inflater,
                          @Nullable ViewGroup container,
                          @Nullable Bundle saveInstanceState) {
        ViewGroup rootView=
                (ViewGroup)inflater.inflate(R.layout.fragment_viewer,container,false);
        imageView=rootView.findViewById(R.id.imageView);
        return rootView;

    }
    public void setImageView(int resId){
        imageView.setImageResource(resId);
 }



}
