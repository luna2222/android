package com.study.android.prj;

import android.os.Bundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class MovieFragment extends Fragment {
    private static final String TAG = "lecture";

    private RecyclerView recyclerView;
    private  MyRecyclerViewAdapter adapter;
    private ArrayList<Movie> movieList;

    LinearLayout add;
    Button addBtn;
    int page = 1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =  inflater.inflate(R.layout.fragment_movie, container, false);

        add = view.findViewById(R.id.add);
        addBtn = view.findViewById(R.id.button);
        movieList = new ArrayList<Movie>();
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                if (!recyclerView.canScrollVertically(-1)) {
                    add.setVisibility(View.GONE);
                    Log.i(TAG, "Top of list");
                } else if (!recyclerView.canScrollVertically(1)) {
                    add.setVisibility(View.VISIBLE);
                    Log.d(TAG, "End of list:" + page);
                    //final RecyclerView fRecyclerView = recyclerView;
                    addBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            ++page;
                            String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=3ac55c9608ceb53e7293820ec08cdab4&language=ko-KR&page="+page;

                            new MyAsyncTask(getContext(), new TaskCompleted() {
                                @Override
                                public void onTaskComplete(Movie[] result) {
                                    for(Movie p : result){
                                        movieList.add(p);
                                    }
                                    adapter = new MyRecyclerViewAdapter(getContext(), movieList);
                                    //fRecyclerView.setAdapter(adapter);
                                }
                            }).execute(url);
                            Log.d(TAG, "로그출력");
                            add.setVisibility(View.GONE);
                            SystemClock.sleep(1000);
                        }
                    });
                } else {
                    add.setVisibility(View.GONE);
                    Log.i(TAG, "idle");
                }
            }
        });



        //API Key 발급 https://api.themoviedb.org
        //Asynctask - OKHttp
        //https://developers.themoviedb.org/3/movies/ 참고자료
        //page당 20개
        //곧 개봉될 영화
        //String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=3ac55c9608ceb53e7293820ec08cdab4&language=ko-KR&page=1";
        //top_rated 가장 많이 본 영화
        //String url = "https://api.themoviedb.org/3/movie/top_rated?api_key=3ac55c9608ceb53e7293820ec08cdab4&language=ko-KR&page=1";
        //현재 상영 중인 영화

        String url = "https://api.themoviedb.org/3/movie/upcoming?api_key=3ac55c9608ceb53e7293820ec08cdab4&language=ko-KR&page="+page;  //MovieDB API

        new MyAsyncTask(getContext(), new TaskCompleted() {
            @Override
            public void onTaskComplete(Movie[] result) {
                for(Movie p : result){
                    movieList.add(p);
                }
                adapter = new MyRecyclerViewAdapter(getContext(), movieList);
                recyclerView.setAdapter(adapter);
            }
        }).execute(url);

        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 4));

        return view;
    }
}
