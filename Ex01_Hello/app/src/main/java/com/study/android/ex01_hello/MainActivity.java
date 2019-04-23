package com.study.android.ex01_hello;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Log.d("lecture", "로그 출력");
                Toast.makeText(getApplicationContext(), "버튼을 눌렸어요", Toast.LENGTH_LONG).show();
            }
        });

    }
    public void onbutton2Clicked(View v) {//버튼 2 인센트 만들어 웹브라우저 띄우기
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://m.naver.com"));
        startActivity(intent);
    }
    public void onbutton3Clicked(View v) {//버튼3 인텐드 만들어 전화하기
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("tel:01074693800"));
        startActivity(intent);
    }
    public void onbutton4Clicked(View v) { //버튼 4 EditText에 입력한 값을 TextView에 보여주기
        //EditText에서 값 읽어와서 TextView 에 할당하기
        EditText editText=findViewById(R.id.editText1);
        TextView textView=findViewById(R.id.textView1);

        textView.setText(editText.getText());
    }

    public void onbutton5Clicked(View v) { //버튼 5 명시적인 인센트 사용으로 새창 열기

    Intent intent=new Intent(getApplicationContext(),New2Activity.class);
    intent.putExtra("CustomerName","홍길동");

    //startActivity(intent);
     startActivityForResult(intent,1);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode,data);

        Log.d(TAG,"콜백함수 호출됨");
        if(requestCode ==1 && resultCode==10)
        {
            String sData="";
            String str="OnActivityResult() called:"+
                    requestCode+":"+
                    resultCode;
            sData= data.getStringExtra("BackData");
            str = str +":"+sData;
            Toast.makeText(getApplicationContext(),str,Toast.LENGTH_SHORT).show();


        }
    }
}
