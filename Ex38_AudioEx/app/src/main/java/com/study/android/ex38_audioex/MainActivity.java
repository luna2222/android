package com.study.android.ex38_audioex;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    MediaPlayer mp = null;
    int playbackPostion = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onBtn1Clicked(View v) {
        mp = MediaPlayer.create(this, R.raw.old_pop);
        mp.seekTo(0);
        mp.start();

    }

    public void onBtn2Clicked(View v) {
        if (mp != null) {
            mp.pause();
            playbackPostion = mp.getCurrentPosition();
        }
    }

    public void onBtn3Clicked(View v) {

        if (mp != null) {
            mp.seekTo(playbackPostion);
            mp.start();
        }
    }

    public void onBtn4Clicked(View v) {

        if (mp != null) {
            mp.stop();
            mp.release();

        }
        mp = null;
    }
    public void onBtn5Clicked(View v) {
        Intent intent =new Intent(getApplicationContext(), RecordActivity.class);
        startActivity(intent);
    }
}
