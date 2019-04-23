package com.study.android.prj;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.ArrayList;
import java.util.Collections;


public class YoutubeAdapter extends RecyclerView.Adapter<YoutubeAdapter.YoutubeItemViewHolder> {
    private static final String TAG = "lecture";

    //-------------------------------------------------------------
    //아이텤 클릭시 함수
    public interface ItemClick{
        public void onClick(View view, int position);
    }
    private ItemClick itemClick;

    //아이템 클릭시 실행 함수 등록 함수
    public void setItemClick(ItemClick itemClick){
        this.itemClick = itemClick;
    }

    Context context;
    ArrayList<Youtube> items = new ArrayList<>();

    public class YoutubeItemViewHolder extends RecyclerView.ViewHolder{

        protected YouTubePlayerView youTubePlayerView;

        public YoutubeItemViewHolder(View view) {
            super(view);
            Log.d(TAG, "222222");
            youTubePlayerView = view.findViewById(R.id.youtube_view);
        }
    }

    public YoutubeAdapter(Context context) {
        this.context = context;
    }

    public void addItem(Youtube item){
        Log.d(TAG, "3333333");
        items.add(item);
    }

    public void removeItem(Youtube item){
        items.remove(item);
    }

    // RecycleView에 새로운 데이터를 보여주기 위해 필요한 ViewHolder를 생성해야 할 때 호출된다.
    @Override
    public YoutubeItemViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType){
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.youtube_item_view, viewGroup, false);

        YoutubeItemViewHolder viewHolder = new YoutubeItemViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull YoutubeItemViewHolder viewHolder, int position) {

        ArrayList<Youtube> items2 = items;
        Collections.reverse(items2);

        String trailer = items2.get(position).getKey();

        // onClick 호출시 섬네일 이미지가 youtubeplayview로 변경되게 하여 플레이? -> 섬네일 이미지는 어디서 가져오나??
        // 또는 다이얼 이용해서 팝업이뜨게 해서 플레이?
        playVideo(trailer, viewHolder.youTubePlayerView); // 키값으로 유튜브 플레이어 뷰에 동영상 호출
    }

    @Override
    public int getItemCount(){
        return (null != items ? items.size() : 0);
    }

    public Object getItem(int position) {
        return items.get(position);
    }

    public void playVideo(final String videoId, YouTubePlayerView youTubePlayerView) {
        //initialize youtube player view
        Log.d(TAG, "trailer: " + videoId);
        youTubePlayerView.initialize("AIzaSyB1r2hQkgT2saCbzmzMjvKyRHcyxCj-PDY",   //구글 API
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {
                        youTubePlayer.cueVideo(videoId);
                    }

                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {
                    }
                });
    }
}
