package com.study.android.ex27_tread1;

import android.app.DownloadManager;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    TextView textView1;
    Button button1;
    ProgressHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //3단계

        handler=new ProgressHandler();

       textView1= findViewById(R.id.textView1);
       button1 =findViewById(R.id.button1);

    }

    public  void onBtnClicked(View v){
        button1 =setEnable(false);

        RequestThread thread =new RequestThread();
        thread.start();
    }
    //1단계
    class RequestThread extends  Thread {
        public void run(){
            for (int i=0; i<20;i++){
                Log.d(TAG,"Request Thread.."+i);
                //1: 쓰레드에서 메인 쓰레드의 객체로의 접근은 불가능
                //textView1.setText("Request Thead.."+i);

                //4 핸들러에 전달할 메세지 작성

                Message msg =handler.obtainMessage();

                Bundle bundle=new Bundle();
                bundle.putString("data1","Request Thread.."+i);
                bundle.putString("data2",String.valueOf(i));
                msg.setData(bundle);

                handler.sendMessage(msg);

                try{

                    Thread.sleep(1000);
                }

            }
        }
    }

    //2단계 테스트-에러가나는 것을 확인
    class  RequestThread extends Thread {
        public  void run(){
            for (int i=0; i<20; i++) {
            Log.d(TAG, "Request Thread .."+i);

            // 1쓰레드에서 메인쓰레드의 객체로의 접근 불가능
                TextView1.setText("Request Thread.."+i);
            }
        }

    }

}
