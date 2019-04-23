package com.study.android.ex04_layout2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    LinearLayout layout1;
    LinearLayout layout2;
    LinearLayout layout3;
    LinearLayout layout4;
    boolean imageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageSelected=false;

        layout1=findViewById(R.id.Layout1);
        layout2=findViewById(R.id.Layout2);
        layout3=findViewById(R.id.Layout3);

    }

    public void onBtn1Clicked(View v) {
        layout1.setVisibility(View.VISIBLE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.GONE);

    }
    public void onBtn2Clicked(View v) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.VISIBLE);
        layout3.setVisibility(View.GONE);

    }
    public void onBtn3Clicked(View v) {
        layout1.setVisibility(View.GONE);
        layout2.setVisibility(View.GONE);
        layout3.setVisibility(View.VISIBLE);

    }
    public void onBtn6Clicked(View v) {
        imageView1=findViewById(R.id.imageView1);
        if(imageSelected)
        {
            imageView1.setImageResource(R.drawable.car);
            imageSelected=false;

        }
        else
        {
            imageView1.setImageResource(R.drawable.house);
            imageSelected=ture;

        }
    }
}
