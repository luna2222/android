package com.study.android.ex02_lifeclycle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class newActivity extends AppCompatActivity {
     private static  final String TAG ="lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new);

        Toast.makeText(getApplicationContext(),"onCreate()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onCreate") ;
    }
    @Override
    protected void onStart(){
        super.onStart();

        Toast.makeText(getApplicationContext(),"onStart()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onStart");
    }
    @Override
    protected void onResume(){
        super.onResume();

        Toast.makeText(getApplicationContext(),"onPesume()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onResume()");
    }
    @Override
    protected void onPause(){
        super.onPause();

        Toast.makeText(getApplicationContext(),"onPause()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onPause");
    }
    @Override
    protected void onStop(){
        super.onStop();
        Toast.makeText(getApplicationContext(),"onStop()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onStop");
    }
    @Override
    protected void onDestroy(){
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"onDestory()호출됨", Toast.LENGTH_SHORT).show();
        Log.d(TAG,"onDestory");
    }
    public void onBtn1Clicked(View v) {
        //현재 인텐트 종료시 인센트에 전달할 데이터 세팅
        Intent intent =new Intent();
        intent.putExtra("BackData","강감찬");
        setResult(10,intent);
        finish();
    }
    }
