package com.study.android.ex46_recognizespeechex;

import android.Manifest;
import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognitionListener;
import android.speech.RecognitionService;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView textView1;
    SpeechRecognizer mRecognizer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView1= findViewById(R.id.textView1);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)
            != PackageManager.PERMISSION_GRANTED){

            if(ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.RECORD_AUDIO)){

            }else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.RECORD_AUDIO},1);
            }
        }
          mRecognizer=SpeechRecognizer.createSpeechRecognizer(this);
          mRecognizer.setRecognitionListener(recognitionListener);

    }
    public  void  onBtn1Clicked(View v){
        try {
            //음성인식 실행
            Intent intent= new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE,getPackageName());
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,"ko_KR");
            //검색 결과를 보여 주는 갯수
            intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS,1);
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT,"음성 검색");

            mRecognizer.startListening(intent);
            }catch (ActivityNotFoundException e){
            Log.d(TAG,"ActivityNotFoundEXception");

        }
    }
    public void  onBtn2Clicked(View v){
        Intent intent= new Intent(getApplicationContext(), Main2Activity.class);
        startActivity(intent);
    }

    // 음성 인식의 결과 (2)
    private RecognitionListener recognitionListener = new RecognitionListener() {
        @Override
        public void onReadyForSpeech(Bundle bundle) {
        }

        @Override
        public void onBeginningOfSpeech() {
        }

        @Override
        public void onRmsChanged(float v) {
        }

        @Override
        public void onBufferReceived(byte[] bytes) {
        }

        @Override
        public void onEndOfSpeech() {
        }

        @Override
        public void onError(int i) {
            textView1.setText("너무 늦게 말하면 오류 뜹니다");
        }

        @Override
        public void onResults(Bundle bundle) {
            String key= "";
            key= SpeechRecognizer.RESULTS_RECOGNITION;
            ArrayList<String>mResult= bundle.getStringArrayList(key);

            String[] rs= new String[mResult.size()];
            mResult.toArray(rs);

            textView1.setText(rs[0]);
        }

        @Override
        public void onPartialResults(Bundle bundle) {
        }

        @Override
        public void onEvent(int i, Bundle bundle) {
        }
    };
}





