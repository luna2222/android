package com.study.android.dustinfo.finedust;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.study.android.dustinfo.MainActivity;
import com.study.android.dustinfo.R;
import com.study.android.dustinfo.data.FineDustRepository;
import com.study.android.dustinfo.data.LocationFineDustRepository;
import com.study.android.dustinfo.model.dust_material.FineDust;

public class FineDustFragment extends Fragment implements FineDustContract.View{

    private TextView mLocationTextView;
    private TextView mTimeTextView;
    private TextView mDustTextView;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private FineDustRepository mRepository;
    private FineDustPresenter mPresenter;

   public  static FineDustFragment newInstance(double lat, double lng){
       Bundle args= new  Bundle();
       args.putDouble("lat",lat);
       args.putDouble("lng",lng);

       FineDustFragment fragment = new FineDustFragment();
       fragment.setArguments(args);
       return fragment;
   }
    public FineDustFragment() {
        // 반드시 필요함
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_fine_dust, container,false);
        mLocationTextView= view.findViewById(R.id.result_location_text);
        mTimeTextView = view.findViewById(R.id.result_time_text);
        mDustTextView =view.findViewById(R.id.result_dust_text);

        if(savedInstanceState != null){
            //복원
            mLocationTextView.setText(savedInstanceState.getString("Location"));
            mTimeTextView.setText(savedInstanceState.getString("time"));
            mDustTextView.setText(savedInstanceState.getString("dust"));
        }
        mSwipeRefreshLayout= view.findViewById(R.id.swipe_refresh_layout);
        mSwipeRefreshLayout.setColorSchemeColors(Color.RED, Color.YELLOW,Color.GREEN,Color.BLUE);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener(){
            @Override
            public  void onRefresh(){
                mPresenter.loadFineDustData();
            }
        });
        return  view;
    }
    @Override
   public  void  onActivityCreated(@Nullable Bundle savedInstanceState){
       super.onActivityCreated(savedInstanceState);
       if(getArguments()!=null){
           double lat= getArguments().getDouble("lat");
           double lng= getArguments().getDouble("lng");
           mRepository= new LocationFineDustRepository(lat,lng);
       }else{
           mRepository=new LocationFineDustRepository();
           ((MainActivity)getActivity()).getLastKnownLocation();
       }
          mPresenter= new FineDustPresenter(mRepository, this);
          mPresenter.loadFineDustData();
   }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("location",mLocationTextView.getText().toString());
        outState.putString("time",mTimeTextView.getText().toString());
        outState.putString("dust",mDustTextView.getText().toString());
    }

    @Override
    public void showFineDustResult(FineDust fineDust) {

           mLocationTextView.setText(fineDust.getWeather().getDust().get(0).getStation().getName());
           mTimeTextView.setText(fineDust.getWeather().getDust().get(0).getTimeObservation());
           mDustTextView.setText(fineDust.getWeather().getDust().get(0).getPm10().getValue() + "µg/m³,"
                    + fineDust.getWeather().getDust().get(0).getPm10().getGrade());

        Log.d("lecture", "1 " + fineDust.getWeather().getDust().get(0).getStation().getName());
        Log.d("lecture", "2 " + fineDust.getWeather().getDust().get(0).getTimeObservation());
        Log.d("lecture", "3 " + fineDust.getWeather().getDust().get(0).getPm10().getValue() + "µg/m³,"
                   + fineDust.getWeather().getDust().get(0).getPm10().getGrade());
   }
    @Override
    public void showLoadError(String message) {
        Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void loadingStart() {
        mSwipeRefreshLayout.setRefreshing(true);
    }

    @Override
    public void loadingEnd() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void reload(double lat, double lng) {
        mRepository= new LocationFineDustRepository(lat,lng);
        mPresenter= new FineDustPresenter(mRepository,this);
        mPresenter.loadFineDustData();
    }
}
