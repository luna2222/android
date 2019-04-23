package com.study.android.ex24_fragment2;


import android.content.Context;
import android.media.Image;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ListFragment extends Fragment {
    private  static  final String TAG="lecture";
   String[] values={"첫번째 이미지", "두번째 이미지","세번째 이미지"};

    public ImageSelectionCallback callback;
    
    @Override
    public void onAttach(Context context){
        super onAttach(context);
        if (context instanceof ImageSectionCallback) {
            callback = (ImageSelectionCallback) context;
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container,
                            @Nullable Bundle savedInstanceState)
    {
        ViewGroup roorView=
                (ViewGroup)inflater.inflate(R.layout.fragment_list,container,false);
        ListView listView=(ListView)roorView.findViewById(R.id.listView);
        ArrayAdapter<String>

        return inflater.inflate(R.layout.fragment_list2, container, false);
    }

}
