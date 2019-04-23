package com.study.android.prj;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieDetailActivity extends YouTubeBaseActivity {
    private static final String TAG = "lecture";

    ArrayList<Youtube> youtubeList;

    YoutubeAdapter adapter;
    RecyclerView youtubeView;

    private YouTubePlayerView youTubeView;
    //private String trailer01;
    private String m_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        youtubeList = new ArrayList<Youtube>();

        Intent intent = getIntent();
        m_id = intent.getStringExtra("id");
        String title = intent.getStringExtra("title");
        String original_title = intent.getStringExtra("original_title");
        String poster_path = intent.getStringExtra("poster_path");
        String overview = intent.getStringExtra("overview");
        String release_date = intent.getStringExtra("release_date");

        Log.d(TAG,"영화ID 코드:" + m_id);

        TextView textView_title = (TextView)findViewById(R.id.tv_title);
        textView_title.setText(title);
        TextView textView_original_title = (TextView)findViewById(R.id.tv_original_title);
        textView_original_title.setText(original_title);
        ImageView imageView_poster = (ImageView) findViewById(R.id.iv_poster);
        Glide.with(this)
                .load("https://image.tmdb.org/t/p/w500"+poster_path)
                .centerCrop()
                .crossFade()
                .into(imageView_poster);

        TextView textView_overview = (TextView)findViewById(R.id.tv_overview);
        textView_overview.setText(overview);
        TextView textView_release_date = (TextView)findViewById(R.id.tv_release_date);
        textView_release_date.setText(release_date);

        //Asynctask 실행
        YoutubeAsyncTask mProcessTask = new YoutubeAsyncTask();
        mProcessTask.execute(m_id);

        adapter = new YoutubeAdapter(this);
        youtubeView = findViewById(R.id.youtube_view);
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
                        Toast.makeText(getApplication(), "트레일러 정보 없음", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public class YoutubeAsyncTask extends AsyncTask<String, Void, Youtube[]> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Youtube[] youtubes) {
            super.onPostExecute(youtubes);

            //ArrayList에 차례대로 집어 넣는다.
            if(youtubes.length > 0){
                for(Youtube p : youtubes){
                    youtubeList.add(p);
                }


                // 유투브 리싸이클 뷰어를 만들어 보자!!
                if (youtubeList.size()>0){
                    for(int i =0 ; i < youtubeList.size() ; i++)
                        adapter.addItem(youtubeList.get(i));
                }
                youtubeView.setAdapter(adapter);
                youtubeView.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
                youtubeView.scrollToPosition(adapter.getItemCount() - 1);

                Log.d(TAG, "유튜브 트레일러 갯수" + youtubeList.size());

                //유튜브뷰어를 이용 화면에 첫번째 트레일러 영상을 출력하자.
//                YouTubePlayerView youTubeView = findViewById(R.id.youtube_view);
//                String trailer01 = youtubeList.get(0).getKey();
//                playVideo(trailer01, youTubeView);
//                Log.d("Youtube", "trailer : " + trailer01);

            }
        }

        @Override
        protected Youtube[] doInBackground(String... strings) {
            String m_id = strings[0];

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url("https://api.themoviedb.org/3/movie/"+m_id+"/videos?api_key=3ac55c9608ceb53e7293820ec08cdab4")
                    .build();
            try {
                Response response = client.newCall(request).execute();
                Gson gson = new GsonBuilder().create();
                JsonParser parser = new JsonParser();
                JsonElement rootObject = parser.parse(response.body().charStream())
                        .getAsJsonObject().get("results");
                Youtube[] posts = gson.fromJson(rootObject, Youtube[].class);
                return posts;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    public void onBtnBackClicked(View v){
        onBackPressed();
    }
}

