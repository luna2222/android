package com.study.android.ex38_audioex;

import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.jar.Manifest;

public class RecordActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    private static String RECORDED_FILE;

    MediaPlayer player;
    MediaRecorder recorder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        File sdcard= Environment.getExternalStorageDirectory();
        File file=new File(sdcard,"recorded.mp3");
        RECORDED_FILE= file.getAbsolutePath();

        //권한 체크후 사용자에 의해 취소 되었다면 다시 요청
        if(ContextCompat.checkSelfPermission(this,
                Manifest.permission.RECORD_AUDIO)
               != PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.RECOED_AUDIO},
                    1);
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String [] { Manifest.permission.READ_EXTERNAL_STORAGE},
              1 );
        }

        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                !=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(
                    this,
                    new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    1);
        }
    }
     public  void onBtn1Clicked(View v){
        if(recorder !=null){
            recorder.stop();
            recorder.release();
            recorder=null;
        }
        recorder=new MediaRecorder();

        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.DEFAULT);

        recorder.setOutputFile(RECORDED_FILE);

        try {
            Toast.makeText(getApplicationContext(), "녹음을 시작합니다",
                    Toast.LENGTH_LONG).show();
            recorder.prepare();
            recorder.start();

        }catch (Exception ex) {
            Log.d(TAG,"Exception: ", ex);


        }
     }
     public  void  onBtn2Clicked(View v){
        if(recorder== null)
            return;

        Toast.makeText(getApplicationContext(), "녹음이 중지 되었습니다",
                Toast.LENGTH_LONG).show();
         recorder.stop();
         recorder.release();
         recorder=null;
     }
     public void  onBtn3Clicked(View v){
        if (player !=null){
            player.stop();
            player.release();
            player=null;
        }

        Toast.makeText(getApplicationContext(), "녹음된 파일을 재생합니다",
                Toast.LENGTH_SHORT).show();
        try {
            player= new MediaPlayer();

            player.setDataSource(RECORDED_FILE);
            player.prepare();
            player.start();
        }catch (Exception e){
            Log.d(TAG,"Audio play failed",e);
        }
     }
     public  void onBtn4Clicked(View v){
        if(player ==null)
            return;

        Toast.makeText(getApplicationContext(), "재생이 중지되었습니다",
                Toast.LENGTH_LONG).show();

        player.stop();
        player.release();
        player=null;

     }
}
