package com.study.android.ex03_layout1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //다음같이 원하는 xml선택적으로 사용할수 있다
       // setContentView(R.layout.activity_main);
        setContentView(R.layout.activity2_main);
    }
}
