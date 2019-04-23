package com.study.android.calculator2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";

    EditText editShow ,editResult;
    Button  Add, Sub, Mul, Div,Remain,Del;
    Button Result;

    Integer  result;

    String history="";  //연산결과를 저장
    String num1="";     // 피연산자1
    String num2="";     //피연산자2
     int type;         //어떤연산자가 선택되었는지 확인하기 위한 int 형type 변수
     int ADD=0;
     int SUB=1;
     int MUL=2;
     int DIV=3;
     int REMAIN=4;

     double d1;
     double d2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        editShow=(EditText)findViewById(R.id.editShow);
        editResult=(EditText)findViewById(R.id.editResult);

        Add=findViewById(R.id.btnAdd);
        Sub=findViewById(R.id.btnSub);
        Mul=findViewById(R.id.btnMul);
        Div=findViewById(R.id.btnDiv);
        Remain = findViewById(R.id.btnRemain);
        Del=findViewById(R.id.btnDel);
        Result= findViewById(R.id.btnResult);

       Add.setOnClickListener(mListener);
       Sub.setOnClickListener(mListener);
       Mul.setOnClickListener(mListener);
       Div.setOnClickListener(mListener);
       Remain.setOnClickListener(mListener);
       Del.setOnClickListener(mListener);

       //초기화
        Button clear=findViewById(R.id.btnClear);
        clear.setOnClickListener(new View.OnClickListener()
        @Override
            public void onClick(View v){
            editShow.setText("");
            editResult.setText("");
            d1 = d2 = 0;
            history = num1 = num2 = "";
        }
        });
      Button PlusMinus= findViewById(R.id.btnPlusMinus);
      PulsMinus.setOnClickListener(new View OnClickListener(){
       @Override
       public  void  onClick(View v){
        //  실수인지 정수인지를 판단해서 부호를 바꾸기
            if((( Double.parseDouble(editResult.getText().toString()))-((int)Double.parseDouble())))
       })
    }
}
