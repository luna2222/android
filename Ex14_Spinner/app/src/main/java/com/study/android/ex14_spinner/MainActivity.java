package com.study.android.ex14_spinner;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "lecture";
     TextView textView;
     String[] items={"mike", "angel","crow","john",
             "ginnie","sally","cohen","rice"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView1);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                    this,android.R.layout.simple_spinner_item,items);
        Spinner spinner=findViewById(R.id.spinner);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {//아이템이 선택 되었을때 호출됨
            @Override
            public void onItemSelected(AdapterView<?> adapterView,
                                       View view, int position, long id) {
               textView.setText(items[position]);
            }
            //아무것도 선택되지 않았을때 호출
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                textView.setText("");
            }
        });

    }
}
