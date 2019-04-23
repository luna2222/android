package com.study.android.ex07_imageview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
    ScrollView scrollView01;
    ImageView imageView01;
    ImageView imageView02;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //이미지 뷰
        imageView01=findViewById(R.id.imageView01);
        imageView02=findViewById(R.id.imageView02);

        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0);

        imageView01.invalidate();
        imageView02.invalidate();
        //스크롤 바의 유무만 달라질뿐 실제로 스크롤은 한다.
        scrollView01 = findViewById(R.id.scrollView01);
        scrollView01.setVerticalScrollBarEnabled(true);
        scrollView01.setHorizontalScrollBarEnabled(true);

    }

    public void onBtn1clicked(View v){imageDown();}

    public void onBtn2clicked(View v){imageUp();}

    protected  void imageDown(){
        imageView01.setImageResource(0);
        imageView02.setImageResource(R.drawable.background1);

        imageView01.invalidate();
        imageView02.invalidate();
    }

    protected  void imageUp(){
        imageView01.setImageResource(R.drawable.background1);
        imageView02.setImageResource(0);

        imageView01.invalidate();
        imageView02.invalidate();
    }
}
